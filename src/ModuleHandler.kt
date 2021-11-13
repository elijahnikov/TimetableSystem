class ModuleHandler {

    fun createModule(
        programme: Programme,
        name: String,
        code: String,
        term: Int,
        year: Int,
    ): Module {

        return Module(
            programme, name, code, term, year
        )
    }

    val moduleList: MutableList<Module> = mutableListOf()

    fun addModule(module: Module) {
        moduleList.add(module)
    }

    fun getModule(mCode: String): Module? {
        for (module in moduleList){
            if (module.code == mCode){
                return module
            }
        }
        return null
    }

}