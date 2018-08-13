

### Option
> ChannelOption.SO_BACKLOG: the maximum queue length for incoming connection indications(a request to connect) is set to the backlog parameter. If a connection indication arrives when the queue is full, the connection is refused.

### option vs childOption:

[stackoverflow](https://stackoverflow.com/questions/35496345/what-is-the-difference-between-serverbootstrap-option-and-serverbootstrap-chil)

##### 1.
The parameters that we set using `ServerBootStrap.option` apply to the ChannelConfig of a newly created ServerChannel,i.e., the server socket which listens for and accepts the client connections.
These options will be set on the Server Channel when bind() or connect() method is called.
`This channel is one per server.`

##### 2.
The `ServerBootStrap.childOption` applies to a channel's channelConfig which gets created once the serverChannel accepts a client connection.
`This channel is per client(or per client socket)`

##### 3.
So `ServerBootStrap.option` parameters apply to the server socket(Server channel) that is listening for connections and `ServerBootStrap.childOption` parameters apply to the socket that gets created once the connection is accepted by the server socket.
