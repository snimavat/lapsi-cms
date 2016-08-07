import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d %level %logger - %msg%n"
    }
}

root(ERROR, ['STDOUT'])
logger("grails.app.controllers.me.nimavat", DEBUG, ['STDOUT'], false)
logger("grails.app.services.me.nimavat", DEBUG, ['STDOUT'], false)
logger("grails.app.tagLibs.me.nimavat", DEBUG, ['STDOUT'], false)
logger("me.nimavat", DEBUG, ['STDOUT'], false)

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}
