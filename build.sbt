name := "github-labels"

scalaVersion in ThisBuild := "2.11.7"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.4.0",
  "com.typesafe.play" %% "play-ws" % "2.4.0",

  "com.jsuereth" %% "scala-arm" % "1.4",

  "com.typesafe.slick" %% "slick" % "3.1.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.0",
  "org.slf4j" % "slf4j-simple" % "1.6.4",

  "mysql" % "mysql-connector-java" % "5.1.37"
)