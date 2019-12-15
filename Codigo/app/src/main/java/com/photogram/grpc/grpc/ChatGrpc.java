package com.photogram.grpc.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
public final class ChatGrpc {

  private ChatGrpc() {}

  public static final String SERVICE_NAME = "Chat";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ChatOuterClass.Usuario,
      ChatOuterClass.Mensaje> getRecibirMensajesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "recibirMensajes",
      requestType = ChatOuterClass.Usuario.class,
      responseType = ChatOuterClass.Mensaje.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<ChatOuterClass.Usuario,
      ChatOuterClass.Mensaje> getRecibirMensajesMethod() {
    io.grpc.MethodDescriptor<ChatOuterClass.Usuario, ChatOuterClass.Mensaje> getRecibirMensajesMethod;
    if ((getRecibirMensajesMethod = ChatGrpc.getRecibirMensajesMethod) == null) {
      synchronized (ChatGrpc.class) {
        if ((getRecibirMensajesMethod = ChatGrpc.getRecibirMensajesMethod) == null) {
          ChatGrpc.getRecibirMensajesMethod = getRecibirMensajesMethod = 
              io.grpc.MethodDescriptor.<ChatOuterClass.Usuario, ChatOuterClass.Mensaje>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Chat", "recibirMensajes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  ChatOuterClass.Usuario.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  ChatOuterClass.Mensaje.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getRecibirMensajesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ChatOuterClass.Mensaje,
      ChatOuterClass.Empty> getEnviarMensajeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "enviarMensaje",
      requestType = ChatOuterClass.Mensaje.class,
      responseType = ChatOuterClass.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ChatOuterClass.Mensaje,
      ChatOuterClass.Empty> getEnviarMensajeMethod() {
    io.grpc.MethodDescriptor<ChatOuterClass.Mensaje, ChatOuterClass.Empty> getEnviarMensajeMethod;
    if ((getEnviarMensajeMethod = ChatGrpc.getEnviarMensajeMethod) == null) {
      synchronized (ChatGrpc.class) {
        if ((getEnviarMensajeMethod = ChatGrpc.getEnviarMensajeMethod) == null) {
          ChatGrpc.getEnviarMensajeMethod = getEnviarMensajeMethod = 
              io.grpc.MethodDescriptor.<ChatOuterClass.Mensaje, ChatOuterClass.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Chat", "enviarMensaje"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  ChatOuterClass.Mensaje.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  ChatOuterClass.Empty.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getEnviarMensajeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatStub newStub(io.grpc.Channel channel) {
    return new ChatStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *rpc iniciarConversacion(Conversacion) returns (Empty);
     * </pre>
     */
    public void recibirMensajes(ChatOuterClass.Usuario request,
        io.grpc.stub.StreamObserver<ChatOuterClass.Mensaje> responseObserver) {
      asyncUnimplementedUnaryCall(getRecibirMensajesMethod(), responseObserver);
    }

    /**
     */
    public void enviarMensaje(ChatOuterClass.Mensaje request,
        io.grpc.stub.StreamObserver<ChatOuterClass.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getEnviarMensajeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRecibirMensajesMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                ChatOuterClass.Usuario,
                ChatOuterClass.Mensaje>(
                  this, METHODID_RECIBIR_MENSAJES)))
          .addMethod(
            getEnviarMensajeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ChatOuterClass.Mensaje,
                ChatOuterClass.Empty>(
                  this, METHODID_ENVIAR_MENSAJE)))
          .build();
    }
  }

  /**
   */
  public static final class ChatStub extends io.grpc.stub.AbstractStub<ChatStub> {
    private ChatStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatStub(channel, callOptions);
    }

    /**
     * <pre>
     *rpc iniciarConversacion(Conversacion) returns (Empty);
     * </pre>
     */
    public void recibirMensajes(ChatOuterClass.Usuario request,
        io.grpc.stub.StreamObserver<ChatOuterClass.Mensaje> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getRecibirMensajesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void enviarMensaje(ChatOuterClass.Mensaje request,
        io.grpc.stub.StreamObserver<ChatOuterClass.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEnviarMensajeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChatBlockingStub extends io.grpc.stub.AbstractStub<ChatBlockingStub> {
    private ChatBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *rpc iniciarConversacion(Conversacion) returns (Empty);
     * </pre>
     */
    public java.util.Iterator<ChatOuterClass.Mensaje> recibirMensajes(
        ChatOuterClass.Usuario request) {
      return blockingServerStreamingCall(
          getChannel(), getRecibirMensajesMethod(), getCallOptions(), request);
    }

    /**
     */
    public ChatOuterClass.Empty enviarMensaje(ChatOuterClass.Mensaje request) {
      return blockingUnaryCall(
          getChannel(), getEnviarMensajeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChatFutureStub extends io.grpc.stub.AbstractStub<ChatFutureStub> {
    private ChatFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ChatOuterClass.Empty> enviarMensaje(
        ChatOuterClass.Mensaje request) {
      return futureUnaryCall(
          getChannel().newCall(getEnviarMensajeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RECIBIR_MENSAJES = 0;
  private static final int METHODID_ENVIAR_MENSAJE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECIBIR_MENSAJES:
          serviceImpl.recibirMensajes((ChatOuterClass.Usuario) request,
              (io.grpc.stub.StreamObserver<ChatOuterClass.Mensaje>) responseObserver);
          break;
        case METHODID_ENVIAR_MENSAJE:
          serviceImpl.enviarMensaje((ChatOuterClass.Mensaje) request,
              (io.grpc.stub.StreamObserver<ChatOuterClass.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getRecibirMensajesMethod())
              .addMethod(getEnviarMensajeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
