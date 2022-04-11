#!groovy

@Library(value = "ms_shared@master", changelog=false) _
springBoot {
	namespace = "rabbits"
	app = "cloud-indexation-agent"
	tier = "backend-server"

	targetPort = "8087"
	readinessHttpGetUrl="/cloud-indexation-agent/health/readiness"
	readinessInitialDelaySeconds="30"
	readinessTimeoutSeconds="5"

	livenessHttpGetUrl="/cloud-indexation-agent/health/liveness"
	livenessInitialDelaySeconds = "80"
	livenessTimeoutSeconds = "5"

	testCommand = "mvn clean test"
}
