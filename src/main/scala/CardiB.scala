object CardiB {
  def realName(): String = {
    "Belcalis Almanzar"
  }
  def iLike(args: String*): String = {
    "I like " + args.mkString(", ")
  }
}