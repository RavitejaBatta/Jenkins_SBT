pipeline {
	agent any
	//agent { docker { image 'maven:3.6.3' } }
	//agent { docker { image 'hseeberger/scala-sbt' } }
    tools {
            jdk 'myJDK'
        }
	environment{

		dockerHome = tool 'myDocker'
        //SBT_HOME = tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'
        //SBT_HOME = tool name: 'ADOP sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'
        //PATH = "${dockerHome}/bin:${env.SBT_HOME}/bin:${env.PATH}"
        sbtHome = tool 'mySBT'
        //SBT_OPTS='-Xmx1024m -Xms512m'
        //SBTOPTS = "${SBT_OPTS} -Dsbt.color=false"
        //SBT_OPTS = "${sbtHome} -Dsbt.color=false"
        //PATH = "${env.SBT_HOME}/bin:${env.PATH}"
        PATH = "$dockerHome/bin:$sbtHome/bin:$PATH"
        //withCredentials([string(credentialsId: 'SECRET_TEXT', variable: 'secret_text')])
        //secret_text = credentials('SECRET_TEXT')
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
				echo "Build"
				echo "$PATH"
				echo "BUILD_NUMBER - $env.BUILD_NUMBER"
				echo "BUILD_ID - $env.BUILD_ID"
				echo "JOB_NAME - $env.JOB_NAME"
				echo "BUILD_TAG - $env.BUILD_TAG"
				echo "My Secret text is $secret_text"
			}
		}


		/*stage('Static Code Analysis') {
        			steps{
        				sh 'mvn pmd:pmd'
        				sh 'mvn checkstyle:checkstyle'
        			    sh 'mvn findbugs:findbugs'
        			}
        		}*/

       // stage('Publish Static Code Analysis') {
        //        	steps{
        //        	recordIssues(tools: [
         //       	    pmdParser(pattern: 'target/pmd.xml'),
         //       	    checkStyle(pattern: 'target/checkstyle-result.xml',reportEncoding: 'UTF-8'),
          //      	    findBugs(pattern: '**/target/findbugsXml.xml',skipSymbolicLinks: true, reportEncoding: 'UTF-8', useRankAsPriority: true)
          //      	    //spotBugs(useRankAsPriority: true)
           //     	    ])
           //     			}
            //    		}

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
		// stage('Integration Test') {
		//	steps{
		//		sh 'mvn failsafe:integration-test failsafe:verify'
		//	}
		// }

		stage('Package') {
			steps{
				sh 'sbt package -DskipTests'
			}
		}
        stage("Application Unit Tests"){
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
                                   reportFiles: 'index.html',
                                   reportName: 'Code Coverage Report'
                                                          ]
                    }
                }
       // stage('Jacoco Code Coverage') {
       // 			steps{
         //               junit '**/*.xml'
           //             jacoco(execPattern:'target/jacoco.exec')
        	//		}
        	//	}
        //stage('Sonarqube Analysis') {
        //        steps{
        //             withSonarQubeEnv(installationName: 'sonarqube'){
        //                 sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.8.0.2131:sonar'
        //                }
        //           }
        //    }
		stage('Build Docker Image') {
			steps{
				//docker build -t myjenkins/jenkinsmicroService:$env.BUILD_TAG
				script{
					dockerImage = docker.build("jenkinservice/scala-sbt-project:$env.BUILD_TAG")
				}
			}
		}

		stage('Push Docker Image') {
			steps{
				script{
                    docker.withRegistry('','dockerhub'){
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
