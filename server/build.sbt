
name := "server"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


val akkaV = "2.4.7"
val akkaHttpVersion = "10.0.9"
val sprayClientV     = "1.3.2"
val json4sVersion = "3.5.2"
val akkaSLF4JVersion = "2.5.6"
val akkaHttpJson4sVersion   = "1.17.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka"   %% "akka-http-core"                    % akkaV,
  "com.typesafe.akka"   %% "akka-http-experimental"            % akkaV,
  "com.typesafe.akka"   %% "akka-http-spray-json-experimental" % akkaV,
  "io.spray"            %% "spray-client"                      % sprayClientV
)

scalacOptions := Seq(
  "-encoding", "utf8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.7",
  "-Xlog-reflective-calls",
  "-Ypatmat-exhaust-depth", "40",
  "-Xmax-classfile-name", "240",
  "-Yrangepos",
  "-optimise"
)

mainClass in Compile := Some("bruno.api.Main")