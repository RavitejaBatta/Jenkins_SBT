case class Person(firstName: String, lastName: String, age: Integer) {

  require(age >= 0, "age should be a positive integer")

  def getFullName = firstName.toLowerCase.capitalize + " " + lastName.toLowerCase.capitalize
}

case class Person_1(firstName: String, lastName: String) {
  def fullName(): String = {
    firstName + " " + lastName
  }
}