# DD2480-assignment-1

### A hypothetical anti-ballistic missile system
A DECIDE()-function was created that uses radar-tracking information (a set of planar data points and a set of conditions) to resolve several _Launch Interceptor Conditions_ (LIC's). DECIDE() then combines these with a _Logical Connector Matrix_ (LCM) in order to consider combinations of LICs and the result is used to generate a launch-unlock signal.

### Structure
The code can be found in the __/src/main__ folder, and all the tests, every LIC has its own test, can be found in the __/src/test__ folder.

We are using Java 8 and JUnit 5.

### Running Tests
Fork/clone/download the project, and then use `make test` to build and run all the tests.

The top-level tests are in `src/test/DecideTest.java`.

### Statement of contributions
* Arthur Carl Vignon
I was added to the group on Wednesday morning after the group had already met one time and spoken about the structure.
We decided together the division of the task and whether or not we should create static fields or an instance of the decide class.
I coded LICs 3, 5, 10 and 11 along with their tests and reviewed a few LICs.
* Axel Larusson
I helped deciding on structure, organization and assign work to all group members. I worked on LIC 1 and LIC 8 and their tests. I also created the first version of the method for the smallest circle which could be used in other LIC's. Finally I always showed up for all group meetings and was active in our messenger group.
* Joel Ekelöf
I've fixed some organization in the repository, such as creating issues and writing the README.
I've also worked on a few LIC's; LIC 0, LIC 7, and LIC 12. Naturally I've been to meetings and been active in the messenger-group, just as everyone else in the group.
* Joel Gille Vikström
I did LIC 2, 6 and 13, as well as utility functions required by these and all associated tests. I have been proactive with regards to the structure of the project and did three different PRs for maintenance purposes. I reviewed the PRs for LIC 1, 4, 7, 8, 9, 12, 14, and the decide function.
* Paul Roland Jacques Griesser
I contributed by talking with the group about the structure of the project and the division of the tasks. I coded three LICs and their tests, the 4th, 9th and 14th. I reviewed code for LICs 0, 5, 6 and merge them to the master branch. I also coded the decide method.
