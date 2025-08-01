SUMMARY = "iceoryx"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=46d6aa0ba1fa2ed247cd8d42f20b72f4"

inherit cmake

DEPENDS = " acl cpptoml"

SRC_URI = "git://git@github.com/eclipse-iceoryx/iceoryx.git;protocol=ssh;branch=main"
SRCREV = "c616c3bd0f4147af6d5b33e89f66136da50d7902"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "-H${S}/iceoryx_meta -DDOWNLOAD_TOML_LIB=OFF -DTOML_CONFIG=OFF"

