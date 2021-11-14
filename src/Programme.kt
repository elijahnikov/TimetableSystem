class Programme (
    var name: String,
    var code: String,
    var type: String,
    val modules: MutableList<Modules> = mutableListOf()
    ) {

    val length: Int = if (type == "Postgraduate"){
        1
    } else {
        3
    }
     init {
         println("Name = $name")
         println("Code = $code")
         println("Type = $type")
         println("Length = $length")
     }

}