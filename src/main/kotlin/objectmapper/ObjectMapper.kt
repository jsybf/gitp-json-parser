package gitp.objectmapper

import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

object ObjectMapper {

    inline fun <reified T : Any> mapList(map: List<Map<String, Any?>>): List<T> {
        val objList: MutableList<T> = mutableListOf()
        for (element in map) {
            objList.add(map(element))
        }

        return objList
    }

    inline fun <reified T : Any> map(map: Map<String, Any?>): T {
        val primaryConstructor = T::class.primaryConstructor ?: throw IllegalStateException()

        // get mapping data from constructor param annotations
        val mappingDataList: MutableList<String> = mutableListOf()
        for (param in primaryConstructor.parameters) {
            val mappingData = param.findAnnotation<MappingData>() ?: throw IllegalStateException()
            mappingDataList.add(mappingData.jsonKey)
        }

        // get all value by jsonKey with order of constructor param
        val constructorParamList: Array<Any?> = mappingDataList
            .map { map[it] }
            .toTypedArray()

        // create object
        val obj: T = primaryConstructor.call(*constructorParamList)

        return obj

    }
}

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class MappingData(
    val jsonKey: String
)