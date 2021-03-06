CREATE TABLE STORIES (
STORY_ID INTEGER PRIMARY KEY,
TITLE VARCHAR(200),
URL VARCHAR(500),
SUBMISSION_TIME INTEGER,
SCORE INTEGER,
USER VARCHAR(50),
DESCENDANTS INTEGER,
SERVED BOOLEAN);

CREATE TABLE COMMENTS (
COMMENT_ID INTEGER PRIMARY KEY,
PARENT INTEGER,
TEXT VARCHAR(5000),
COMMENT_USER VARCHAR(50),
CHILD_COMMENTS INTEGER,
FOREIGN KEY (PARENT) REFERENCES STORIES(STORY_ID));