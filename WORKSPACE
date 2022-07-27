workspace(name = "platform")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

#-----------------------------------------------------------------------------
# Jvm External to use maven packages
#-----------------------------------------------------------------------------
RULES_JVM_EXTERNAL_TAG = "2.8"

RULES_JVM_EXTERNAL_SHA = "79c9850690d7614ecdb72d68394f994fef7534b292c4867ce5e7dec0aa7bdfad"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

#-----------------------------------------------------------------------------
# GRPC
#-----------------------------------------------------------------------------
IO_GRPC_TAG = "1.48.0"

http_archive(
    name = "io_grpc_grpc_java",
    sha256 = "769c191dc663090e71d6a938bfb6166c1ad40e8471ccd385034faa627d774a9e",
    strip_prefix = "io_grpc_grpc_java-%s" % IO_GRPC_TAG,
    urls = ["https://github.com/grpc/grpc-java/archive/v%s.zip" % IO_GRPC_TAG],
)

#load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")
#
#grpc_java_repositories()

#-----------------------------------------------------------------------------
# proto
#-----------------------------------------------------------------------------
http_archive(
    name = "com_google_protobuf",
    sha256 = "1e622ce4b84b88b6d2cdf1db38d1a634fe2392d74f0b7b74ff98f3a51838ee53",
    strip_prefix = "protobuf-3.8.0",
    urls = ["https://github.com/google/protobuf/archive/v3.8.0.zip"],
)

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")

protobuf_deps()

#-----------------------------------------------------------------------------
# Project Dependencies pinned
#-----------------------------------------------------------------------------
maven_install(
    artifacts = [
        "com.google.code.findbugs:jsr305:1.3.9",
        "com.google.errorprone:error_prone_annotations:2.0.18",
        "com.google.j2objc:j2objc-annotations:1.1",
    ],
    #    override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)
