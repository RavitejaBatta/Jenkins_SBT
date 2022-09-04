pipeline {
	agent any
	//agent { docker { image 'maven:3.6.3' } }
	//agent { docker { image 'hseeberger/scala-sbt' } }
    tools {
            jdk 'myJDK'
        }
	environment{

		dockerHome = tool 'myDocker'
        sbtHome = tool 'mySBT'
        PATH = "$dockerHome/bin:$sbtHome/bin:$PATH"
     }

	stages{

		stage('Checkout') {
		    environment{
		            secret_text = credentials('SECRET_TEXT')
		    }
			steps{
				sh 'sbt --version'
				sh 'docker version'
				sh 'java -version'
				sh "printenv | sort"
				sh 'docker logout'
				echo "Build"
				echo "$PATH"
				echo "BUILD_NUMBER - $env.BUILD_NUMBER"
				echo "BUILD_ID - $env.BUILD_ID"
				echo "JOB_NAME - $env.JOB_NAME"
				echo "BUILD_TAG - $env.BUILD_TAG"
				echo "My Secret text is $secret_text"
			}
		}

		stage('Compile') {
        			steps{
        				sh 'sbt clean compile'
        			}
        		}

		stage('Test') {
			steps{
				sh 'sbt test'
			}
		}

        stage('ScalaCheckStyle') {
        			steps{
        				sh 'sbt scalastyle'
        			}
        		}

		stage('Package') {
			steps{
				sh 'sbt package -DskipTests'
			}
		}
        stage("Scoverage"){
                    steps{
                        sh "sbt clean coverage test coverageReport"
                    }
                }
        stage("Publishing covarage reports"){
               steps{

                    step([$class: 'ScoveragePublisher', reportDir: 'target/scala-2.11/scoverage-report', reportFile: 'scoverage.xml'])
                    publishHTML target: [
                                   allowMissing: true,
                                   alwaysLinkToLastBuild: false,
                                   keepAll: true,
                                   reportDir: 'target/scala-2.11/scoverage-report',
                                   reportFiles: '*.html',
                                   reportName: 'Code Coverage Report'
                                                          ]
                    }
                }
        stage('Build Docker Image') {
        			steps{
        				//docker build -t myjenkins/jenkinsmicroService:$env.BUILD_TAG
        				script{
        					dockerImage = docker.build("jenkinservice/jenkins-sbt-project:$env.BUILD_TAG")
        				}
        			}
        		}
	stage('Push Docker Image') {
        			steps{
        				//docker build -t myjenkins/jenkinsmicroService:$env.BUILD_TAG
        				script{
						docker.withRegistry('','jenkinservice'){
						dockerImage.push();
						dockerImage.push('latest');
						}
        				}
        			}
        		}

	} 
	
	post{
        always{
			echo 'I run always'
		}
		success{
			echo 'I run when you are success'
		}
		failure{
			echo 'I run when you fail'
		}
		//unstable: When the test failure happens
	}
}
