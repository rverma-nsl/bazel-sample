workspace(name = "platform")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

#-----------------------------------------------------------------------------
# Go Toolchain
#-----------------------------------------------------------------------------
http_archive(
    name = "io_bazel_rules_go",
    sha256 = "16e9fca53ed6bd4ff4ad76facc9b7b651a89db1689a2877d6fd7b82aa824e366",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.34.0/rules_go-v0.34.0.zip",
        "https://github.com/bazelbuild/rules_go/releases/download/v0.34.0/rules_go-v0.34.0.zip",
    ],
)

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")

go_rules_dependencies()

go_register_toolchains(version = "1.18.4")

#-----------------------------------------------------------------------------
# Gazelle Tools
#-----------------------------------------------------------------------------
http_archive(
    name = "bazel_gazelle",
    sha256 = "501deb3d5695ab658e82f6f6f549ba681ea3ca2a5fb7911154b5aa45596183fa",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.26.0/bazel-gazelle-v0.26.0.tar.gz",
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.26.0/bazel-gazelle-v0.26.0.tar.gz",
    ],
)

load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")

gazelle_dependencies()

#-----------------------------------------------------------------------------
# Java Toolchain
#-----------------------------------------------------------------------------
http_archive(
    name = "rules_java",
    sha256 = "8c376f1e4ab7d7d8b1880e4ef8fc170862be91b7c683af97ca2768df546bb073",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_java/releases/download/5.0.0/rules_java-5.0.0.tar.gz",
        "https://github.com/bazelbuild/rules_java/releases/download/5.0.0/rules_java-5.0.0.tar.gz",
    ],
)

load("@rules_java//java:repositories.bzl", "rules_java_dependencies", "rules_java_toolchains")

rules_java_dependencies()

rules_java_toolchains()

#-----------------------------------------------------------------------------
# Jvm External to use maven packages
#-----------------------------------------------------------------------------
http_archive(
    name = "rules_jvm_external",
    sha256 = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca",
    strip_prefix = "rules_jvm_external-4.2",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.2.zip",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

#-----------------------------------------------------------------------------
# Proto Toolchain
#-----------------------------------------------------------------------------
http_archive(
    name = "rules_proto",
    sha256 = "e017528fd1c91c5a33f15493e3a398181a9e821a804eb7ff5acdd1d2d6c2b18d",
    strip_prefix = "rules_proto-4.0.0-3.20.0",
    urls = [
        "https://github.com/bazelbuild/rules_proto/archive/refs/tags/4.0.0-3.20.0.tar.gz",
    ],
)

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")

rules_proto_dependencies()

rules_proto_toolchains()

#-----------------------------------------------------------------------------
# Grpc Toolchain
#-----------------------------------------------------------------------------
http_archive(
    name = "io_grpc_grpc_java",
    sha256 = "88b12b2b4e0beb849eddde98d5373f2f932513229dbf9ec86cc8e4912fc75e79",
    strip_prefix = "grpc-java-1.48.1",
    urls = ["https://github.com/grpc/grpc-java/archive/v1.48.1.tar.gz"],
)

load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS", "grpc_java_repositories")

grpc_java_repositories()

#-----------------------------------------------------------------------------
# Maven packages
#-----------------------------------------------------------------------------
load("//:repositories.bzl", "PLATFORM_JAVA_ARTIFACTS")

maven_install(
    artifacts = PLATFORM_JAVA_ARTIFACTS + IO_GRPC_GRPC_JAVA_ARTIFACTS,
    generate_compat_repositories = True,
    override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
    repositories = [
        "https://repo.maven.apache.org/maven2/",
    ],
)

load("@maven//:compat.bzl", "compat_repositories")

compat_repositories()

#-----------------------------------------------------------------------------
# StackB Gazelle setup
#-----------------------------------------------------------------------------
http_archive(
    name = "build_stack_rules_proto",
    sha256 = "16c93fe75314f21f8ef786f27668e2d796400950df1fcd7d249be426420405ac",
    strip_prefix = "rules_proto-9bea22f9fe0bb81c9cbfa855e6bf979faa73f742",
    urls = ["https://github.com/stackb/rules_proto/archive/9bea22f9fe0bb81c9cbfa855e6bf979faa73f742.tar.gz"],
)

register_toolchains("@build_stack_rules_proto//toolchain:prebuilt")

load("@build_stack_rules_proto//:go_deps.bzl", "gazelle_protobuf_extension_go_deps")

gazelle_protobuf_extension_go_deps()

#-----------------------------------------------------------------------------
# JVM Contrib Rules
#-----------------------------------------------------------------------------
http_archive(
    name = "contrib_rules_jvm",
    sha256 = "310ddfdb224a308a6ca14383c589e03395a789474e5d47a81b63ba96470c07cc",
    strip_prefix = "rules_jvm-cf264660d763e336ecc0772bdbf11fb0eaecc1fd",
    url = "https://github.com/bazel-contrib/rules_jvm/archive/cf264660d763e336ecc0772bdbf11fb0eaecc1fd.zip",
)

load("@contrib_rules_jvm//:repositories.bzl", "contrib_rules_jvm_deps", "contrib_rules_jvm_gazelle_deps")

contrib_rules_jvm_deps()

contrib_rules_jvm_gazelle_deps()

load("@contrib_rules_jvm//:setup.bzl", "contrib_rules_jvm_setup")

contrib_rules_jvm_setup()

load("@contrib_rules_jvm//:gazelle_setup.bzl", "contrib_rules_jvm_gazelle_setup")

contrib_rules_jvm_gazelle_setup()
