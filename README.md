### How to build package and send it to Launchpad.net:

* apt-get install git dpkg-dev debhelper dput

* Setup your public GPG key that's registered in Launchpad
   * Create new key: `gpg --gen-key`
   * Or import already existing key: `gpg --import /path/to/gpg_private.key`
   * Check local keys: `gpg --list-secret-keys`
   * Send it to Ubuntu keyserver: `gpg --send-keys --keyserver keyserver.ubuntu.com <PUB_HEX8_ID>`
   * Select OpenPGP key on your personal Launchpad page
   * Note: my own key that I use for signing Whitesource packages is `2FDF8A4B 2016-03-17 Ilya Evseev (lp:whitesource) <ilya.evseev@gmail.com>`

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
 
### Hot to get and check JAR dependencies:

* Install the latest http://tattletale.jboss.org/
* Put JAR file to separate directory
* cd /path/to/tattletale
* java -Xmx512m -jar tattletale.jar /path/to/whitesource/ /path/to/created/report/
* cd /path/to/whitesource-fs-agent-dpkg
* javac check_tattle_deps.java
* CLASSPATH=. java check_tattle_deps /path/to/created/report/dependson/index.html
* add JARs to CLASSPATH until all FAIL's converted to good's
* put resulting list to **[whitesource-fs-agent](whitesource-fs-agent)** file.
