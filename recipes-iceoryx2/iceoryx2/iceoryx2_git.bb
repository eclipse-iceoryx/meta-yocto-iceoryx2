SUMMARY = "iceoryx2"
LICENSE = "Apache-2.0 | MIT"
LIC_FILES_CHKSUM = "file://LICENSE-APACHE;md5=22a53954e4e0ec258dfce4391e905dac \
                    file://LICENSE-MIT;md5=b377b220f43d747efdec40d69fcaa69d"

# Enable network for the compile task allowing cargo to download dependencies
do_compile[network] = "1"

DEPENDS = " iceoryx"

SRC_URI = "git://github.com/eclipse-iceoryx/iceoryx2.git;protocol=https;branch=main"
SRCREV = "70c925416482c821df04d564e3b8ecf91cd1191c"

S = "${WORKDIR}/git"

INSANE_SKIP:${PN} += " already-stripped"
FILES_SOLIBSDEV = ""

# This should actually be handled by meta-rust-bin most likely
# in classes/cargo_bin.bbclass similar to Yocto Poky
# meta/classes-recipe/rust-common.bbclass populating RUSTFLAGS
# to prevent bitbake warnings "contains reference to TMPDIR [buildpaths]"
# At this time, meta-rust-bin allows to pass in additional flags
# via EXTRA_RUSTFLAGS to populate RUSTFLAGS
RUST_DEBUG_REMAP = "--remap-path-prefix=${WORKDIR}=${TARGET_DBGSRC_DIR}"
EXTRA_RUSTFLAGS = "${RUST_DEBUG_REMAP}"

inherit cargo_bin

CARGO_FEATURES = "libc_platform"

IOX2_STAGING_DIR = "${STAGING_DIR}/iceoryx2-ffi-artifacts"

do_install() {
    install -d ${IOX2_STAGING_DIR}
    install -m 0755 ${CARGO_BINDIR}/libiceoryx2_ffi.so ${IOX2_STAGING_DIR}
    install -m 0755 ${CARGO_BINDIR}/libiceoryx2_ffi.a ${IOX2_STAGING_DIR}

    install -d ${IOX2_STAGING_DIR}/iceoryx2-ffi-cbindgen/include/iox2
    install -m 0755 ${CARGO_BINDIR}/iceoryx2-ffi-cbindgen/include/iox2/iceoryx2.h ${IOX2_STAGING_DIR}/iceoryx2-ffi-cbindgen/include/iox2
}
