SUMMARY = "cpptoml is a header-only library for parsing TOML"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8739ce451f437fa9493b37a405fb16f1"

inherit cmake
EXTRA_OECMAKE += "-DCPPTOML_BUILD_EXAMPLES=OFF"

SRC_URI = "git://github.com/skystrife/cpptoml.git;protocol=https;branch=master"
SRCREV = "fededad7169e538ca47e11a9ee9251bc361a9a65"

S = "${WORKDIR}/git"

