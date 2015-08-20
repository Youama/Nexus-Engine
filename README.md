# Nexus Engine

> *Nexus Engine is a semantic search engine implemented in Java under MIT license. The engine currently is in development stage and it doesn't have any release. The current development stage is about the creating of the base architect of the software to support modularity, different type of databases, the main operating systems, the main platforms and the main Java versions and to build automation testing system what can aid  developers. You can learn more about the project in this Readme file.*

[![Build Status](https://travis-ci.org/Youama/Nexus-Engine.svg?branch=master)](https://travis-ci.org/Youama/Nexus-Engine) [![codecov.io](http://codecov.io/github/Youama/Nexus-Engine/coverage.svg?branch=master)](http://codecov.io/github/Youama/Nexus-Engine?branch=master)
[![Javadoc](https://img.shields.io/badge/docs-Javadoc-brightgreen.svg)](http://youama.github.io/nexus-engine-javadoc)
[![MIT License](https://img.shields.io/badge/license-MIT-33CCFF.svg)](http://opensource.org/licenses/MIT)
[![Join the chat at https://gitter.im/Youama/Nexus-Engine](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/Youama/Nexus-Engine?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Motivation
The purpose of this project to develop a software what can answer basic questions without the question or answer would be pre-programmed or forced in any source code or file. It should be like Cortana or Siri but much more flexible and open source.

For this nearly impossible goals this software has to contain many features:
* **Crawler**: The ability to mapping a part of the Internet in multi-thread operation.
* **Scheduler**: It stores tasks and runs them. It should be like the GruntJs but more sophisticated.
* **Parser**: Parses files, retrieves the clear text from HTML or offline document.
* **WSD/Lesk/WordNet**: Kind of *machine learning*. It orders words to sentences and validates them by Human or by Google with searching. Every inputs and outputs should change the relation between the words by verify the correct results and deny the non-sense results.
* **Cache**: All searching query should be able to cached into dummy database tables.
* **CLI**: It provides access to search engine by command line interface. This should be suitable to integrate the search engine to another languages by executable calls.

So many modules are required before the search engine could work covered by an intelligent interface - a kind of *a.i.*

## LICENSE
This project/software has MIT license as you can see in the LICENSE file. It means Nexus Engine is an open source project.
