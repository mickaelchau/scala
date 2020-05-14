#!/bin/csh -f

find . -type f -name '*.scala' -print | xargs -I % -n 1 cp -v ../scalain_e/scalain-e-course-code/% %

