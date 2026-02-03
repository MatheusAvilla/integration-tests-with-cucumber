select * from item

INSERT INTO item (id, name, price, category, description)
SELECT
  gs,
  concat('a dummy name ', (random() * 10000)),
  round((random() * 1000)::numeric, 2),
  concat('a dummy category ', (random() * 1000)),
  concat('a dummy description ', (random() * 1000))
FROM generate_series(1, 200000) AS gs;