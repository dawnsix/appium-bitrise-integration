BITRISE CIA TEST

NOTE: to get whitebox/code execution from test in Java8 + Mvn working on AWS device farm w/ Appium@next (v2+):

0. issue version updates via bash cmd for node, npm, and appium in the yml
1. create esconfig with deps and versions from app under test + match compose version
2. disable apk siging in capabilities
3. sync gradle builds locally, build espresso server locally and copy apk
4. upload as auxillary app
5. update default appium commands with vendor prefixes
x. to debug, route stdout/err into a file and cat
