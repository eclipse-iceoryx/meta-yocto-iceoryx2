SUMMARY = "iceoryx2bindings"
LICENSE = "Apache-2.0 | MIT"
LIC_FILES_CHKSUM = "file://LICENSE-APACHE;md5=22a53954e4e0ec258dfce4391e905dac \
                    file://LICENSE-MIT;md5=b377b220f43d747efdec40d69fcaa69d"

inherit cmake

DEPENDS = " python3 iceoryx iceoryx2"

SRC_URI = "git://github.com/eclipse-iceoryx/iceoryx2.git;protocol=https;branch=main \
           file://0001-add-BUILD_SHARED_LIBS-cmake-variable.patch"
SRCREV = "f3a45d41a625f1e8867798d88812a33308e705ff"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += " -DRUST_BUILD_ARTIFACT_PATH=${RECIPE_SYSROOT}/usr/lib"
EXTRA_OECMAKE += " -DCMAKE_INSTALL_PREFIX=${D}/${exec_prefix}"
EXTRA_OECMAKE += " -DBUILD_SHARED_LIBS=False"

FILES_${PN}-staticdev += "${libdir}/libiceoryx2_cxx.a"
RDEPENDS_${PN}-dev += "${PN}-staticdev"
BBCLASSEXTEND = "native nativesdk"

do_install() {  
  cmake --install ${S}/../build
}

do_install:append() {
  rm -rf ${D}${libdir}/libiceoryx2_ffi.a
}
