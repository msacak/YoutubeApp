YoutubeApp
- Login olmadan video izlencek, izlenme sayısına eklenecek. User login olduğunda yorumda yapabilecek.
- Login olmadan video yükleyemezsin, yorum yapamazsın vs.
- Entity: Video,User, Comment, Like(içinde user_id ve video_id)
  Like (id,user_like_id
- Beğenide many to many ilişkisi olacak.
- dto olacak???
  -Gui paketi
  gui -> controller -> service

implementation 'org.postgresql:postgresql:42.7.3'