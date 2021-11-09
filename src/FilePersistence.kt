class FilePersistence: Persistence() {

    override fun save(data: String){

        println("Saving $data to file")

    }

}