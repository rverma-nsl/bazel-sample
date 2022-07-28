workspace(name = "platform")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

#-----------------------------------------------------------------------------
# Jvm External to use maven packages
#-----------------------------------------------------------------------------
RULES_JVM_EXTERNAL_TAG = "4.2"

RULES_JVM_EXTERNAL_SHA = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

load("@rules_jvm_external//:defs.bzl", "maven_install")

#-----------------------------------------------------------------------------
# GRPC
#-----------------------------------------------------------------------------
IO_GRPC_TAG = "1.48.0"

http_archive(
    name = "io_grpc_grpc_java",
    sha256 = "769c191dc663090e71d6a938bfb6166c1ad40e8471ccd385034faa627d774a9e",
    strip_prefix = "grpc-java-%s" % IO_GRPC_TAG,
    urls = ["https://github.com/grpc/grpc-java/archive/v%s.zip" % IO_GRPC_TAG],
)

load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS", "grpc_java_repositories")

grpc_java_repositories()

rules_java_version = "6e03a930850249d4c982d236fbed6091bf283c4e"

# rules_java defines rules for generating Java code from Protocol Buffers.
http_archive(
    name = "rules_java",
    #    sha256 = "9a72d1bade803e1913d1e0a6f8beb35786fa3e8e460c98a56d2054200b9f6c5e",
    strip_prefix = "rules_java-%s" % rules_java_version,
    urls = [
        "https://github.com/bazelbuild/rules_java/archive/%s.tar.gz" % rules_java_version,
    ],
)

#-----------------------------------------------------------------------------
# proto
#-----------------------------------------------------------------------------
rules_proto_version = "fcad4680fee127dbd8344e6a961a28eef5820ef4"

# rules_proto defines abstract rules for building Protocol Buffers.
http_archive(
    name = "rules_proto",
    sha256 = "36476f17a78a4c495b9a9e70bd92d182e6e78db476d90c74bac1f5f19f0d6d04",
    strip_prefix = "rules_proto-%s" % rules_proto_version,
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_proto/archive/%s.tar.gz" % rules_proto_version,
        "https://github.com/bazelbuild/rules_proto/archive/%s.tar.gz" % rules_proto_version,
    ],
)

load("@com_google_protobuf//:protobuf_deps.bzl", "PROTOBUF_MAVEN_ARTIFACTS", "protobuf_deps")

protobuf_deps()

#-----------------------------------------------------------------------------
# Go Tools
#-----------------------------------------------------------------------------
http_archive(
    name = "io_bazel_rules_go",
    sha256 = "ab21448cef298740765f33a7f5acee0607203e4ea321219f2a4c85a6e0fb0a27",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/archive/refs/tags/v0.32.0.zip",
        "https://github.com/bazelbuild/rules_go/archive/refs/tags/v0.32.0.zip",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "5982e5463f171da99e3bdaeff8c0f48283a7a5f396ec5282910b9e8a49c0dd7e",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.25.0/bazel-gazelle-v0.25.0.tar.gz",
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.25.0/bazel-gazelle-v0.25.0.tar.gz",
    ],
)

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")
load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")

go_register_toolchains(version = "1.18.2")

go_rules_dependencies()

gazelle_dependencies()

#-----------------------------------------------------------------------------
# Platform Artifacts
#-----------------------------------------------------------------------------
load("//:repositories.bzl", "PLATFORM_JAVA_ARTIFACTS", "PLATFORM_JAVA_OVERRIDE")
load("@bazel_skylib//lib:dicts.bzl", "dicts")

maven_install(
    artifacts = IO_GRPC_GRPC_JAVA_ARTIFACTS + PLATFORM_JAVA_ARTIFACTS,
    generate_compat_repositories = True,
    override_targets = dicts.add(IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS, PLATFORM_JAVA_OVERRIDE),
    repositories = [
        "https://repo.maven.apache.org/maven2/",
    ],
)

load("@maven//:compat.bzl", "compat_repositories")

compat_repositories()

#-----------------------------------------------------------------------------
# Docker Tools
#-----------------------------------------------------------------------------
http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "b1e80761a8a8243d03ebca8845e9cc1ba6c82ce7c5179ce2b295cd36f7e394bf",
    urls = ["https://github.com/bazelbuild/rules_docker/releases/download/v0.25.0/rules_docker-v0.25.0.tar.gz"],
)

load("@io_bazel_rules_docker//toolchains/docker:toolchain.bzl", docker_toolchain_configure = "toolchain_configure")

docker_toolchain_configure(
    name = "docker_config",
)

load("@io_bazel_rules_docker//repositories:repositories.bzl", container_repositories = "repositories")

container_repositories()

load("@io_bazel_rules_docker//repositories:deps.bzl", container_deps = "deps")

container_deps()

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

#-----------------------------------------------------------------------------
# Docker Java Imge
#-----------------------------------------------------------------------------
container_pull(
    name = "java_base",
    # 'tag' is also supported, but digest is encouraged for reproducibility.
    digest = "sha256:deadbeef",
    registry = "gcr.io",
    repository = "distroless/java",
)

load("@io_bazel_rules_docker//java:image.bzl", "java_image")

#-----------------------------------------------------------------------------
# Remote Build Execution
#-----------------------------------------------------------------------------
# http_archive(
#     name = "bazel_toolchains",
#     sha256 = "4598bf5a8b4f5ced82c782899438a7ba695165d47b3bf783ce774e89a8c6e617",
#     strip_prefix = "bazel-toolchains-0.27.0",
#     urls = [
#         "https://mirror.bazel.build/github.com/bazelbuild/bazel-toolchains/archive/0.27.0.tar.gz",
#         "https://github.com/bazelbuild/bazel-toolchains/archive/0.27.0.tar.gz",
#     ],
# )
# load("@bazel_toolchains//rules:rbe_repo.bzl", "rbe_autoconfig")
# # Creates a default toolchain config for RBE.
# # Use this as is if you are using the rbe_ubuntu16_04 container,
# # otherwise refer to RBE docs.
# rbe_autoconfig(name = "rbe_default")
