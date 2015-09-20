# Nexus Engine Specification

## About specification
Nexus Engine as a software has base rules and there are imaginations about the future functionality. These rules and thoughts are about the software design and purpose and they usually change while code also changes. So the specification is not a kind of limitation but described possibilities. The non-master branches can avoid this specified rules, but violate the specification is not recommended.

## Functionality
* Nexus Engine is a Search Engine.
* It searches by text in text and give text results. 
* It should be able to mapping, indexing, weighting, connecting millions of words.
* It should support multiple languages only by *system scopes*.
* It can be capable to give a relevant answer to a regular question without the question or answer would be stored.
* It has to recognize the objects in the texts (WSD).
* It should contain a unique WordNet (not the standard WordNet implementation).
* It should contain a Crawler what can gather data automatically and put them to the WordNet.
* It should contain a Scheduler to manage the features and tasks.

## The language
Nexus Engine is a Java based development. The development tools can contain Bash scripts or Python/Jython scripts but these scripts can not be essential for development. The code of the applications and modules can contains only Java and Scala, but the Core module shouldn't contain Scala.

## Coding standard
* Nexus' source code should follow the Oracle's Java coding standards.
* A line can not be longer than 120 character.
* A method should be small as it can be small or smaller.

## Portability and compatibility
Nexus Engine has to be capable to ported in different ways:

Operation Systems:
* Linux
* Windows
* OS X
* Android

Platforms:
* Client Side - mobile
* Server Side - Web app and standalone Linux service

JDKs:
* Open JDK 7
* Oracle 7
* Oracle 8

Databases:
* HSQL
* MySQL
* PostgreSQL

## Searching
Nexus as a search engine has a standardization to searching, it is called as *Searchlet*. The Searchlet is the standard way to implement any searching methodology inside Nexus Engine: [Searchlet API documentation](http://youama.github.io/nexus-engine-javadoc/com/youama/nexus/core/search/package-summary.html)

## Modularity
Nexus Engine is a modular system. All of them is a Maven module. There are two kind of modules inside Nexus:
* Applications: Only one application can run in the same time. The application initiate the Search Engine.
* Modules: Tools for the searching.

The modules and applications should contain a bean configuration file to declare the entities. The modules should avoid to depend on each other, but all of them have to depend on the Core module.

## Modules
* **Wordlex**: Advanced technology to replace the old WordNet specification.

## Continuous Integration
The Continuous Integration is automated and configured in travis.yml. This file can not be modified in any Git Branches.

## Security and tests
All public methods should be tested or called in all possible ways in way of jUnit test.

## Documentation
All class and method should contain Javadoc at least one line description.

## Contribution
* Any contribution is welcomed.
* The contributor's name will be appended to the author list.

## License
Nexus Engine has MIT license. It can never be other in this repository.

*More information on https://github.com/Youama/Nexus-Engine/wiki*

