class ModuleHandler
{

    fun createModule(
        programme: Programme,
        name: String,
        code: String,
        programmeCode: String,
        term: Int,
        year: Int,
    ): Modules
    {

        return Modules(
            programme, name, code, programmeCode, term, year
        )
    }

    //list to store all instances of module
    val modulesList: MutableList<Modules> = mutableListOf()

    //add module to list
    fun addModule(modules: Modules)
    {
        modulesList.add(modules)
    }

    //get specific module by its code
//    fun getModule(mCode: String): Modules?
//    {
//        for (module in modulesList)
//        {
//            if (module.code == mCode)
//            {
//                return module
//            }
//        }
//        return null
//    }

//    fun getModuleByProgramme(pCode: String): MutableList<Modules>
//    {
//
//        val list: MutableList<Modules> = mutableListOf()
//
//        for (module in modulesList)
//        {
//            if (module.programmeCode == pCode)
//            {
//                list.add(module)
//            }
//        }
//
//        return list
//    }

    fun getModule(mCode: String): Modules?
    {
        return modulesList.find { m -> m.code == mCode }
    }

    fun getModuleByProgramme(pCode: String): MutableList<Modules>
    {
        return modulesList.filter { m -> m.programmeCode == pCode} as MutableList<Modules>
    }



}