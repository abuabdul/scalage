name := "scala-swift"

version := "0.0.2-SNAPSHOT"

libraryDependencies ++= Seq(
	"uk.co.bigbeeconsultants" % "bee-client_2.10" % "0.22.7",
	"io.spray" % "spray-json_2.10" % "1.2.5",
	"junit" % "junit" % "4.11" % "test",
	"org.scalatest" % "scalatest_2.10" % "2.0.M6-SNAP8" % "test"
)

resolvers ++= Seq(
	"big bee consultants" at "http://repo.bigbeeconsultants.co.uk/repo",
	"spray" at "http://repo.spray.io"
)