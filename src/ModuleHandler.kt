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

    val modulesList: MutableList<Modules> = mutableListOf()

    fun addModule(modules: Modules)
    {
        modulesList.add(modules)
    }

    fun getModule(mCode: String): Modules?
    {
        for (module in modulesList)
        {
            if (module.code == mCode)
            {
                return module
            }
        }
        return null
    }

    fun getModuleByProgramme(pCode: String): MutableList<Modules>
    {

        val list: MutableList<Modules> = mutableListOf()

        for (module in modulesList)
        {
            if (module.programmeCode == pCode)
            {
                list.add(module)
            }
        }

        return list
    }

}