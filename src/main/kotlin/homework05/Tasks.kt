package homework05

fun restoreAlphabet(words: List<String>): String = "qwerty"

fun evenTrees(n: Int, edges: List<Pair<Int, Int>>): Int = -1

fun dijkstra(n: Int, edges: List<Triple<Int, Int, Int>>, startNode: Int): List<Int> = emptyList()

fun floydWarshall(n: Int, edges: List<Triple<Int, Int, Int>>): List<List<Int>> {
    val dist = getMatrixOfNearests(n, edges)
    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j])
            }
        }
    }
    return dist.toList()
}

fun getListOfNearests(n: Int, edges: List<Triple<Int, Int, Int>>): List<ArrayList<Pair<Int, Int>>> {
    val listOfNearests: List<ArrayList<Pair<Int, Int>>> = List(n, { ArrayList<Pair<Int, Int>>() })

    for (i in 0 until edges.size) {
        listOfNearests[edges[i].first].add(Pair(edges[i].second, edges[i].third))
    }
    return listOfNearests
}

fun getMatrixOfNearests(n: Int, edges: List<Triple<Int, Int, Int>>): Array<MutableList<Int>> {
    val matrix = Array(n, { MutableList(n, { Int.MIN_VALUE }) })

    for (i in 0 until n) {
        matrix[i][i] = 0
    }

    for (edge: Triple<Int, Int, Int> in edges) {
        matrix[edge.first][edge.second] = edge.third
    }
    return matrix
}