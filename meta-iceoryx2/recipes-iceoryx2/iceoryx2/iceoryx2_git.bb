SUMMARY = "Iceoryx2"
LICENSE = "Apache-2.0 | MIT"
LIC_FILES_CHKSUM = "file://LICENSE-APACHE;md5=22a53954e4e0ec258dfce4391e905dac \
		    file://LICENSE-MIT;md5=b377b220f43d747efdec40d69fcaa69d"

# Enable network for the compile task allowing cargo to download dependencies
do_compile[network] = "1"

DEPENDS = " iceoryx"

RDEPENDS:${PN} = "python3-core"

SRC_URI = "git://github.com/eclipse-iceoryx/iceoryx2.git;protocol=https;branch=main"
SRCREV = "f3a45d41a625f1e8867798d88812a33308e705ff"

INSANE_SKIP:${PN} += " already-stripped"

S = "${WORKDIR}/git"

# This should actually be handled by meta-rust-bin most likely
# in classes/cargo_bin.bbclass similar to Yocto Poky 
# meta/classes-recipe/rust-common.bbclass populating RUSTFLAGS
# to prevent bitbake warnings "contains reference to TMPDIR [buildpaths]"
# At this time, meta-rust-bin allows to pass in additional flags
# via EXTRA_RUSTFLAGS to populate RUSTFLAGS
RUST_DEBUG_REMAP = "--remap-path-prefix=${WORKDIR}=${TARGET_DBGSRC_DIR}"
EXTRA_RUSTFLAGS = "${RUST_DEBUG_REMAP}"

inherit setuptools3 cargo_bin

CARGO_FEATURES = "libc_platform"

ALLOW_EMPTY:${PN} = "1"
RDEPENDS:${PN}-dev = "${PN}"
RRECOMMENDS:${PN}-dev = "${PN}-staticdev"
FILES:${PN}-dev += "${libdir}/iceoryx2-ffi-cbindgen/include/iox2"

do_install() {
  install -d ${D}${libdir}
  install -m 0755 ${CARGO_BINDIR}/libiceoryx2_ffi.a ${D}${libdir}

  install -d ${D}${includedir}/iox2
  install ${CARGO_BINDIR}/iceoryx2-ffi-cbindgen/include/iox2/iceoryx2.h ${D}${includedir}/iox2

  install -d ${D}${libdir}/iceoryx2-ffi-cbindgen/include/iox2
  install ${CARGO_BINDIR}/iceoryx2-ffi-cbindgen/include/iox2/iceoryx2.h ${D}${libdir}/iceoryx2-ffi-cbindgen/include/iox2
}

