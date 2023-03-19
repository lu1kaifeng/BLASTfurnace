# BLAST furnace
So, my professor asked me to build a site to run BLAST on NUMTS database in java, which proved to be rather challenging.
There is no viable BLAST implementation in java and the only semi-applicable tool is magicBLAST which is written is C++ and is an executable, not a library. Knowing nothing about bioinformatics, I cannot convert it into a library, so I had to use alignment tool in biojava to build the website. Only one problem, the algorithm is too slow and cannot be used in real time. So, I parallelized the process using **ForkJoinTask** and distributed the workload to multiple machines. It worked, HOWEVER:

 - The algorithm DOES NOT produce **E-value**
 - Database has to be split with the provided data splitter  into separate JSON file.
 
 
