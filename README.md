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

### How to install package from Launchpad.net:

* echo "deb http://ppa.launchpad.net/whitesource/whitesource-fs-agent/ubuntu trusty main" >> /etc/apt/sources.list.d/Whitesource.list
* apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv C339C2C0
* apt-get update
* apt-get install whitesource-fs-agent
* customize **/etc/default/whitesource** and **/etc/whitesource/whitesource-fs-agent.config** files
* run **/usr/bin/whitesource-fs-agent**
