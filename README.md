## Shortener

A very simple URL shortener API built with Jersey. Using a tomcat container.

# Assumptions:

- No accounts/authentication.
- No analytics.
- Links can persist forever.
- Ids are always auto-generated.
- Only http* links are supported.

# Structure:

 - ShortURL CRUD resource:
   
   - Get.
   - Create.
   - Update.
   - Delete.
   - Redirect.

 - Data model:

   - id . randomly generated used as the "link id" as well at the end of the URL.
   - longURL - destination URL being shortened.
   - date - creation date timestamp. (may be used for partitioning).

   - Field indexes:

     - id
     - date

# ID Generation Strategy

7 alphanumeric chars case-sensitive. Gives at least 2.4 billion different ids. There are up to 10 id generation retries in case of collision. 


# Run it:

Assumes you have a postgres running on port 15432.

Create tables:

```
postgres=# CREATE USER shortener WITH PASSWORD 'sh0rt3nr';
CREATE ROLE
postgres=# CREATE DATABASE shortener;
CREATE DATABASE

CREATE TABLE shorturl (
	id varchar(255) NOT NULL,
	"date" timestamp NULL,
	longurl varchar(400) NULL,
	CONSTRAINT shorturl_pkey PRIMARY KEY (id)
);

CREATE INDEX shorturl_date_idx ON shorturl USING btree (date);
```



This is an bare bones POC. A MVP would also have:

- A "last visit" date for each url to possibly partition or Cache urls based on this.
- Rate limiters.
- User accounts (with a corresponding index by user).




