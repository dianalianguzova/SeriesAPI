# SeriesAPI 
REST API для поиска информации о сериалах, актерах этих сериалов и их наградах.

# 🌐 API endpoints
## Series
| Method | Endpoint | Description | 
|-------------|----------|-------------|
| GET | `/api/series` | Получить все сериалы |
| GET | `/api/series/{id}` | Получить сериал по ID | 
| GET | `/api/series/{seriesId}/actors/{actorsId}` | Получить актёра из сериала |
| GET | `/api/series/{id}/scenario` | Получить сценарий сериала |
| POST | `/api/series` | Создать новый сериал | 
| POST | `/api/series/{seriesId}/actors/{actorId}` | Добавить актёра к сериалу | 
| PUT | `/api/series/{id}` | Обновить сериал | 
| PUT | `/api/series/{id}/scenario` | Обновить сценарий сериала | 
| DELETE | `/api/series/{id}` | Удалить сериал |

## Awards
| Method | Endpoint | Description | 
|-------------|----------|-------------|
| GET | `/api/awards` | Получить все награды |
| GET | `/api/awards/{id}` | Получить награду по ID |
| GET | `/api/awards/{id}/actor` | Получить актёра по его награде |
| POST | `/api/awards` | Создать новую награду |
| PUT | `/api/awards/{id}` | Обновить награду | 
| DELETE | `/api/awards/{id}` | Удалить награду | 

## Actors 
| Method | Endpoint | Description |
|-------------|----------|-------------|
| GET | `/api/actors` | Получить всех актёров |
| GET | `/api/actors/{id}` | Получить актёра по ID |
| GET | `/api/actors/{actorsId}/series/{seriesId}` | Получить сериал актёра | 
| POST | `/api/actors` | Создать нового актёра |
| PUT | `/api/actors/{id}` | Обновить данные актёра | 
| DELETE | `/api/actors/{id}` | Удалить актёра |

