SUMMARY = "publishsubscribe"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit cmake

DEPENDS = " iceoryx2 iceoryx2bindings"

S = "${WORKDIR}/publishsubscribe-0.1"

FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI = "file://publishsubscribe.tar.gz"

do_install() {
  install -d ${D}${bindir}
  install -m 755 ${S}/../build/example_c_publisher ${D}${bindir}
  install -m 755 ${S}/../build/example_c_subscriber ${D}${bindir}
}
