import homework01.*

import homework03.backpack
import homework03.minimalDiffDivide
import homework03.minimalScalarProduct
import homework04.autocomplete
import homework04.differentSubstrings
import homework04.manacker

import homework03.*
import homework04.*
import homework05.*

import kotlin.reflect.KParameter
import kotlin.reflect.jvm.javaMethod
import kotlin.reflect.jvm.kotlinFunction

fun main(args: Array<String>) {
    val functions = listOf(
            ::howManyNumbers.javaMethod?.kotlinFunction,
            ::binarySearch.javaMethod?.kotlinFunction,
            ::getSumOfPrime.javaMethod?.kotlinFunction,
            ::countTriples.javaMethod?.kotlinFunction,
            ::findUnique.javaMethod?.kotlinFunction,
            ::ternarySearch.javaMethod?.kotlinFunction,

            ::minimalDiffDivide.javaMethod?.kotlinFunction,
            ::minimalScalarProduct.javaMethod?.kotlinFunction,
            ::backpack.javaMethod?.kotlinFunction,

            ::differentSubstrings.javaMethod?.kotlinFunction,
            ::manacker.javaMethod?.kotlinFunction,
            ::autocomplete.javaMethod?.kotlinFunction,

            ::restoreAlphabet.javaMethod?.kotlinFunction,
            ::evenTrees.javaMethod?.kotlinFunction,
            ::dijkstra.javaMethod?.kotlinFunction,
            ::floydWarshall.javaMethod?.kotlinFunction
    )
    val funcName = readLine()
    if (funcName == null || !functions.any{ it?.name == funcName}) {
        println("No such function")
        return
    }
    val currentFunction = functions.find { it?.name == funcName }
    var params: Map<KParameter, Any?> = mapOf()
    currentFunction?.parameters?.forEach {
        when {
            it.type.toString() == "kotlin.collections.List<kotlin.Int>" -> {
                val len = getInt() ?: return
                val list = getListOfInt() ?: return
                if (list.size != len) {
                    println("Incorrect size of list")
                    return
                }
                params = params.plus(Pair(it, list))
            }
            it.type.toString() == "kotlin.Int" -> {
                val int = getInt() ?: return
                params = params.plus(Pair(it, int))
            }
            it.type.toString() == "kotlin.String" -> {
                val string = getString() ?: return
                params = params.plus(Pair(it, string))
            }
            it.type.toString() == "kotlin.collections.List<kotlin.String>" -> {
                val list = getListOfString() ?: return
                params = params.plus(Pair(it, list))
            }
            it.type.toString() == "kotlin.collections.List" +
                    "<kotlin.Triple<kotlin.Int, kotlin.Int, kotlin.Int>>" -> {
                val list = getListOfTriples() ?: return
                params = params.plus(Pair(it, list))
            }
            it.type.toString() == "kotlin.collections.List" +
                    "<kotlin.Pair<kotlin.Int, kotlin.Int>>" -> {
                println("Beginning to parse")
                val list = getListOfPairs() ?: return
                params = params.plus(Pair(it, list))
            }
            else -> {
                println("I can't do anything with ${it.name}")
                return
            }
        }
    }
    println(currentFunction?.callBy(params))
}

fun getInt(): Int? {
    return try {
        readLine()!!.toInt()
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

fun getListOfPairs(): List<Pair<Int, Int>>? {
    return try {
        readLine()!!.split(';')
                    .map{it.split(' ').filter {it.isNotEmpty()}}
                    .filter {it.isNotEmpty()}
                    .map {Pair(it[0].toInt(), it[1].toInt())}
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

fun getListOfTriples(): List<Triple<Int, Int, Int>>? {
    return try {
        readLine()!!.split(';')
                    .map{it.split(' ').filter {it.isNotEmpty()}}
                    .filter {it.isNotEmpty()}
                    .map {Triple(it[0].toInt(), it[1].toInt(), it[2].toInt())}
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

fun getListOfInt(): List<Int>? {
    return try {
        readLine()!!.split(' ').map{it.toInt()}
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

fun getString(): String? {
    return try {
        readLine()!!
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

fun getListOfString(): List<String>? {
    return try {
        readLine()!!.split(' ')
    } catch (e: Exception) {
        println(e.message)
        null
    }
}
