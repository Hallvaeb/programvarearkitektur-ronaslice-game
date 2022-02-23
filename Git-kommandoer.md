Første gang, initialisering og pull:

git init .

git remote add origin https://github.com/Hallvaeb/TDT4240_progark_prosjekt.git

git branch --set-upstream-to=origin/master master

git pull

Deretter skal det bare være å skrive: 

git pull

For å pushe første gang:

git add .

git commit -m "Beskjed"

git push --set-upstream origin master


Deretter:

git add .

git commit -m "egendefinert beskjed: make this change"

git push
