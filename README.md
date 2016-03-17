### How to build package and send it to Launchpad.net:

* apt-get install git dpkg-dev debhelper

* Setup your public GPG key that's registered in Launchpad
   * gpg --import /path/to/gpg_private.key
   * My own key is: 2FDF8A4B 2016-03-17 Ilya Evseev (lp:whitesource) <ilya.evseev@gmail.com>

* git clone https://github.com/ilyaevseev/whitesource-fs-agent-dpkg && cd whitesource-fs-agent-dpkg

* Put to this directory an actual version of whitesource-fs-agent-A.B.C.D.jar (and remove previous)

* Add entry to debian/changelog:
    * version should match JAR version A.B.C.D
    * signer should match PGP userid "Full Name (comment) <email@deneg.net>"

* dpkg-buildpackage -S

* dput ppa:whitesource/whitesource-fs-agent  ../whitesource-fs-agent_A.B.C.D-X_source.changes
