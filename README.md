# sof

Так был создан этот проект:

    cd ~repos
    git clone git@github.com:mem-memov/sof.git
    sbt -sbt-version 1.2.8 new http4s/http4s.g8 -b 0.20
    cd sof

Так запускается сервер:

    cd ~repos/sof
    sbt run