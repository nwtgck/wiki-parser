lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.mindfulmachines",
      scalaVersion := "2.10.4",
      version      := "1.0-SNAPSHOT"
    )),
    name := "wiki-parser",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-library" % "2.10.4",
      "org.scalatest" %% "scalatest" % "2.2.4",
      "org.apache.spark" %% "spark-core" % "1.5.2",
      "org.apache.spark" %% "spark-sql" % "1.5.2",
      "org.apache.spark" %% "spark-mllib" % "1.5.2",
      "org.apache.hadoop" % "hadoop-client" % "2.4.0",
      "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.16",
      "info.bliki.wiki" % "bliki-core" % "3.1.0",
      "org.jsoup" % "jsoup" % "1.8.3",
      "org.scalanlp" %% "breeze" % "0.10",
      "net.java.dev.jets3t" % "jets3t" % "0.9.4"
    )
  )