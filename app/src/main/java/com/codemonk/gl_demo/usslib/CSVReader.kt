package com.codemonk.gl_demo.usslib

import java.io.*
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.ArrayList

class CSVReader(private var inputStream: InputStream) {


    fun read(): Array<DoubleArray> {
        var resultList: Array<DoubleArray> = arrayOf()
        var bufferReader: BufferedReader = inputStream.bufferedReader(Charsets.UTF_8)

        try {
            var csvLine: String

            var csvLines: List<String> = bufferReader.readLines()


            resultList = csvLines.map { line ->
                 line.split(",").map { s-> s.toDouble() }.toDoubleArray()
            }.toTypedArray()

//            while ((bufferReader.readLine().also { csvLine = it }) != null) {
//                val row: DoubleArray =
//                    csvLine.split(",").map { s -> s.toDouble() }.toDoubleArray()
//                resultList.add(row)
//            }
        } catch (e: IOException) {
            throw RuntimeException("Error reading CSV File $e")
        } catch (e: Exception) {
            throw Exception("Exception: $e")
        } finally {
            inputStream.close()
        }
        return resultList
    }
}