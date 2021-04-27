#!/bin/csh -f

alias STDERR 'bash -c "cat - 1>&2"'
cd ~/Repos/scala-e-student

if (! -d .git/) then
  echo must run in top level dir of git repo | STDERR
  exit 0
endif

find . -type f -name '*.scala' -print | xargs -I % -n 1 rsync -i ../scalain_e/scalain-e-course-code/% %
find . -type f -name '*.sc'    -print | xargs -I % -n 1 rsync -i ../scalain_e/scalain-e-course-code/% %

# rsync SRC DST
rsync -ri  ./src/main/templates/. ./src/main/scala/homework/. 


