#!/bin/sh

case "$1" in
	start)
		docker compose -f docker-compose.yml up -d
;;
	stop)
		docker compose -f docker-compose.yml stop
;;
	restart)
		docker compose -f docker-compose.yml restart
;;
	down)
		docker compose -f docker-compose.yml down -v
;;
	recreate)
		docker compose -f docker-compose.yml down -v
		docker compose -f docker-compose.yml up -d
esac
exit 0