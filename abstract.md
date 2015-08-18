# Abstract specification

(These thoughts must moved to WIKI in the future.)

It's a kind of Semantic Search

* nexus-crawler
* nexus-word: Indexing words
* nexus-wordnet: The WordNet concept.
* nexus-wsd: Compute with words, possible dependency with nexus-words module

Nexus Word module just gets the text resources from the crawler and stores the words in indexed structure.

The Nexus WordNet module gets lexical data from Nexus Words module or by another more controlled way. But this module should be not depend on Nexus Words module.

Nexus WSD should recognize the objects from texts like car, house, brother, etc. English language rules should be implemented before. It has to know the user's intent. WSD use the Words and WordNet modules as resources.

WSD module should implement the Lesk Algorithm. It should be the first dummy version of Q/A a.i. in Nexus
