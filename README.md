# wiki-parser
[![Build Status](https://travis-ci.org/nwtgck/wiki-parser-scala.svg?branch=master)](https://travis-ci.org/nwtgck/wiki-parser-scala)

Provides methods for parsing various Wikipedia data sources (articles, click stream, page views) in Apache Spark and Scala.

The details are here: <http://mindfulmachines.io/blog/2015/12/20/wikipedia-data-in-spark>


## How to import `wiki-parser`

### `build.sbt`

Write your `build.sbt` like the following.

```scala
lazy val root = project.in(file(".")).
  // (from: https://github.com/sbt/sbt/issues/3489)
  dependsOn(RootProject(uri("git://github.com/nwtgck/wiki-parser-scala.git#656a0c84c3edeb7233abd249d2d98a02a43b67a6"))).
  settings(
    inThisBuild(List(
      organization := "io.github.nwtgck",
      scalaVersion := "2.10.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "wiki-parser-prac-scala",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    fork := true // (from: https://github.com/saurfang/sbt-spark-submit/issues/4)
  )
```

## Example usage



```scala
import java.io.File

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import _root_.io.mindfulmachines.wiki_parser.wiki


object Main{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName( "SparkTest" )
      .setMaster("local[*]" )
      .set("spark.executor.memory", "1g")
    val sc    = new SparkContext( conf )

    val rawPages: RDD[(Long, String)] =
      // ("wikidump.xml" from: https://raw.githubusercontent.com/nwtgck/wiki-parser-scala/master/src/test/resources/wikidump.xml)
      wiki.Parser.readWikiDump(sc, "wikidump.xml")

    val pages   : RDD[(Long, wiki.Parser.Page)] =
      wiki.Parser.parsePages(rawPages)

    println(pages.count)
  }
}
```

### Example project using `wiki-parser`

https://github.com/nwtgck/wiki-parser-prac-scala