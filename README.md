opclientmanager
===============

Operation Client Manager Framework. A Framework to manage client invocations on provided operations.
Operation could be remote web service operation, local and remote EJB operation or general object method invocations. 

Relevant features:
* Simple configuration, in developing, test and production environments
* High performances with local thread safe cached clients artefacts 
* High abstraction with simple programmatic model
* Synchronous and Asynchronous Payload mep
* Support for non blocking io 

![Class Diagram](/src/doc/oclientmanager.png)

Here a performance comparison between pure SEI JAX-WS client and optclientmanager OperationDispatcher:

    - 1000 invocation to remote web service:
        * JAX-WS SEI client: 13125 msec
        * OperationDispatcher: 2998 msec

More than 10 time faster!!
    