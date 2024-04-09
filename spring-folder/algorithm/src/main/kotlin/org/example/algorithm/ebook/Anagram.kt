package org.example.algorithm.ebook

class Anagram {
    fun anagramsOf(str: String): ArrayList<String> {
        println("str: $str")
        if (str.length == 1) return arrayListOf(str)

        val tmp = arrayListOf<String>()
        val anagrams = anagramsOf(str.substring(1, str.length))
        println("anagrams: $anagrams")
        anagrams.forEach {
            (0..it.length).forEach { index ->
                val copy = it
                val new = copy.replaceRange(index, index, str[0].toString())
                tmp.add(new)
                println("[$str][$it][$index] new = $copy + ${str[0]} = $new")
            }
        }
        return tmp
    }
}
