# **Test Microservices based Application with Dockerized Containers**

**Overview:**

For any Complex Enterprise level Application built on top of microservices, we should try testing its different layers, integrations and behaviours separately without loosing its granularity and context.
The best way to achieve this is to run your automated tests with containerized instances of their required dependencies without worrying about the complex setups and configs etc.

With TestContainers, you can run your Application in a short-lived test mode and easily create an Application Under Test state with required dependencies in containerized manner.
TestContainers providing lightweight, throwaway instances of common dependencies or anything else that can run in a Docker container such as databases, web servers, browser clients and even message queues.

