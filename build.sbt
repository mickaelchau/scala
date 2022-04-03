name := "scalain-e-course-code"

version := "0.1"

scalaVersion := "2.13.8"
libraryDependencies += "junit" % "junit" % "4.10" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"
libraryDependencies += "org.scalafx" %% "scalafx" % "15.0.1-R21"
libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.7.0"

scalacOptions ++= Seq(
  "-Ytasty-reader", "-deprecation"
  )



// added the following lines as per advice from
// https://stackoverflow.com/questions/57628274/sbt-wont-compile-helloworld-scalafx-example-complains-about-javafx-missing-fro/57628433#57628433
// begin
lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

// Add JavaFX dependencies
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map{ m=>
  "org.openjfx" % s"javafx-$m" % "14.0.1" classifier osName
}
// end

val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
  ).map(_ % circeVersion)



resolvers += "4 jvm repr" at "https://maven.scijava.org/content/repositories/public/"

libraryDependencies += ("io.github.quafadas" %% "dedav4s" % "0.1.2").cross(CrossVersion.for2_13Use3)
