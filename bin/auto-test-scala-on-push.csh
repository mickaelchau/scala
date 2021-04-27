#!/bin/csh -f
alias STDERR 'bash -c "cat - 1>&2"'

echo ============== setting up java 11 ==============

which java

java -version


echo ============== running  scala tests ==============

cd .
echo pwd= `pwd`
set tmpfile = sbt.$$.out

# master branch
# on enhancements branch we DO NOT RUN the enhancement tests
#sbt -Dsbt.log.noformat=true "set parallelExecution in Test := false" "testOnly -- -l enhancement" |& tee $tmpfile

# enhancements branch
# on enhancements branch we DO RUN the enhancement tests
sbt -Dsbt.log.noformat=true "set parallelExecution in Test := false" testOnly |& tee $tmpfile

grep -e '^[[]error[]]' $tmpfile
if ($status == 0) then
  echo === BEGIN content of $tmpfile ===
  cat -n $tmpfile | STDERR
  echo === END content of $tmpfile ===
  echo error running tests  | STDERR
  exit 1
endif
