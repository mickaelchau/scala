// Copyright (c) 2020 EPITA Research and Development Laboratory
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge,
// publish, distribute, sublicense, and/or sell copies of the Software,
// and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package lecture

object MetroData {
  val stationPositions:Vector[(String,Int,Int)] = Vector(
    // station-name, x-position, y-position, useful for drawing a metro map
    ("Abbesses", 308, 536 ),
    ("Alexandre Dumas", 472, 386 ),
    ("Alma Marceau", 193, 404 ),
    ("Alésia", 290, 244 ),
    ("Anatole France", 138, 517 ),
    ("Anvers", 324, 521 ),
    ("Argentine", 149, 442 ),
    ("Arts et Métiers", 374, 414 ),
    ("Arts et Métiers", 373, 417 ),
    ("Assemblée Nationale", 258, 382 ),
    ("Aubervilliers-Pantin Quatre Chemins", 433, 595 ),
    ("Avenue Émile Zola", 194, 326 ),
    ("Avron", 472, 372 ),
    ("Barbès Rochechouart", 350, 521 ),
    ("Barbès Rochechouart", 350, 521 ),
    ("Basilique de Saint-Denis", 343, 646 ),
    ("Bastille", 403, 360 ),
    ("Bastille", 403, 361 ),
    ("Bastille", 403, 362 ),
    ("Bel Air", 471, 324 ),
    ("Belleville", 428, 456 ),
    ("Belleville", 428, 456 ),
    ("Bercy", 437, 296 ),
    ("Bercy", 437, 296 ),
    ("Bibliothèque François Mitterand", 424, 268 ),
    ("Billancourt", 77, 270  ),
    ("Bir-Hakeim", 172, 353 ),
    ("Blanche", 278, 521 ),
    ("Bobigny Pablo Picasso", 522, 593 ),
    ("Bobigny-Pantin Raymond Queneau", 498, 574 ),
    ("Boissière", 160, 406 ),
    ("Bolivar", 426, 498 ),
    ("Bonne Nouvelle", 339, 452 ),
    ("Bonne Nouvelle", 340, 454 ),
    ("Botzaris", 457, 492 ),
    ("Boucicaut", 185, 289 ),
    ("Boulogne Jean Jaurès", 79, 316  ),
    ("Boulogne Pont de Saint-Cloud Rond Point Rhin et Danube", 67, 307  ),
    ("Bourse", 314, 434 ),
    ("Brochant", 249, 544 ),
    ("Bréguet-Sabin", 408, 376 ),
    ("Buttes Chaumont", 435, 492 ),
    ("Buzenval", 477, 365 ),
    ("Bérault", 532, 329 ),
    ("Cadet", 322, 484 ),
    ("Cambronne", 217, 328 ),
    ("Campo-Formio", 376, 278 ),
    ("Cardinal Lemoine", 350, 329 ),
    ("Carrefour Pleyel", 314, 622 ),
    ("Censier Daubenton", 356, 288 ),
    ("Champs Élysées Clémenceau", 237, 414 ),
    ("Champs Élysées Clémenceau", 237, 413 ),
    ("Chardon Lagâche", 134, 318 ),
    ("Charenton-Écoles", 483, 264 ),
    ("Charles Michels", 178, 326 ),
    ("Charles de Gaulle Étoile", 170, 436 ),
    ("Charles de Gaulle Étoile", 170, 437 ),
    ("Charles de Gaulle Étoile", 170, 435 ),
    ("Charonne", 442, 383 ),
    ("Chaussée d'Antin La Fayette", 284, 472 ),
    ("Chaussée d'Antin La Fayette", 284, 472 ),
    ("Chemin Vert", 397, 383 ),
    ("Chevaleret", 404, 269 ),
    ("Château Landon", 384, 484 ),
    ("Château Rouge", 350, 532 ),
    ("Château d'Eau", 364, 462 ),
    ("Château de Vincennes", 547, 324 ),
    ("Châtelet", 324, 386 ),
    ("Châtelet", 324, 383 ),
    ("Châtelet", 323, 380 ),
    ("Châtelet", 324, 382 ),
    ("Châtelet", 322, 378 ),
    ("Châtillon-Montrouge", 229, 193 ),
    ("Cité", 317, 366 ),
    ("Cluny La Sorbonne", 319, 340 ),
    ("Colonel Fabien", 416, 472 ),
    ("Commerce", 196, 311 ),
    ("Concorde", 257, 407 ),
    ("Concorde", 258, 406 ),
    ("Concorde", 256, 407 ),
    ("Convention", 202, 273 ),
    ("Corentin Celton", 192, 242 ),
    ("Corentin-Cariou", 422, 562 ),
    ("Corvisart", 355, 259 ),
    ("Cour Saint-Émilion", 439, 281 ),
    ("Courcelles", 202, 462 ),
    ("Couronnes", 439, 442 ),
    ("Crimée", 419, 550 ),
    ("Croix de Chavaux", 523, 402 ),
    ("Créteil-Préfecture", 529, 207 ),
    ("Créteil-Université", 522, 216 ),
    ("Créteil-l'Echat Hôpital Henri Mondor", 516, 224 ),
    ("Danube", 468, 501 ),
    ("Daumesnil", 461, 316 ),
    ("Daumesnil", 461, 316 ),
    ("Denfert Rochereau", 300, 277 ),
    ("Denfert Rochereau", 302, 276 ),
    ("Dugommier", 448, 304 ),
    ("Dupleix", 180, 344 ),
    ("Duroc", 238, 336 ),
    ("Duroc", 238, 336 ),
    ("Edgar Quinet", 276, 295 ),
    ("Esplanade de la Défense", 91, 461  ),
    ("Europe", 242, 483 ),
    ("Exelmans", 112, 301 ),
    ("Faidherbe-Chaligny", 432, 352 ),
    ("Falguière", 253, 312 ),
    ("Filles du Calvaire", 397, 413 ),
    ("Fort d'Aubervilliers", 438, 608 ),
    ("Franklin D. Roosevelt", 214, 421 ),
    ("Franklin D. Roosevelt", 214, 421 ),
    ("Félix Faure", 192, 300 ),
    ("Gabriel Péri Asnières-Gennevilliers", 198, 607 ),
    ("Gaité", 259, 282 ),
    ("Galliéni", 527, 429 ),
    ("Gambetta", 484, 429 ),
    ("Gambetta", 484, 429 ),
    ("Gare d'Austerlitz", 389, 317 ),
    ("Gare d'Austerlitz", 389, 317 ),
    ("Gare de Lyon", 428, 331 ),
    ("Gare de Lyon", 418, 321 ),
    ("Gare de l'Est", 367, 484 ),
    ("Gare de l'Est", 368, 484 ),
    ("Gare de l'Est", 367, 484 ),
    ("Gare du Nord", 364, 510 ),
    ("Gare du Nord", 369, 507 ),
    ("Garibaldi", 281, 596 ),
    ("George V", 192, 428 ),
    ("Glacière", 337, 265 ),
    ("Goncourt", 413, 445 ),
    ("Grande Arche de la Défense", 77, 465  ),
    ("Guy Môquet", 267, 560 ),
    ("Havre Caumartin", 268, 465 ),
    ("Havre Caumartin", 268, 465 ),
    ("Hoche", 468, 550 ),
    ("Hôtel de Ville", 356, 375 ),
    ("Hôtel de Ville", 356, 375 ),
    ("Invalides", 237, 389 ),
    ("Invalides", 237, 387 ),
    ("Iéna", 175, 397 ),
    ("Jacques Bonsergent", 386, 448 ),
    ("Jasmin", 129, 352 ),
    ("Jaurès", 415, 508 ),
    ("Jaurès", 416, 509 ),
    ("Jaurès", 416, 508 ),
    ("Javel", 158, 326 ),
    ("Jourdain", 449, 474 ),
    ("Jules Joffrin", 331, 554 ),
    ("Jussieu", 367, 324 ),
    ("Jussieu", 367, 324 ),
    ("Kléber", 166, 422 ),
    ("La Chapelle", 372, 521 ),
    ("La Courneuve 8 Mai 1945", 441, 619 ),
    ("La Fourche", 259, 533 ),
    ("La Motte Picquet Grenelle", 205, 332 ),
    ("La Motte Picquet Grenelle", 204, 332 ),
    ("La Motte Picquet Grenelle", 203, 333 ),
    ("La Muette", 139, 384 ),
    ("La Tour-Maubourg", 220, 373 ),
    ("Lamarck Caulaincourt", 314, 553 ),
    ("Laumière", 430, 519 ),
    ("Le Kremlin-Bicêtre", 385, 217 ),
    ("Le Peletier", 301, 484 ),
    ("Ledru Rollin", 415, 359 ),
    ("Les Gobelins", 353, 272 ),
    ("Les Halles", 337, 404 ),
    ("Les Sablons", 118, 453 ),
    ("Liberté", 475, 274 ),
    ("Liège", 258, 497 ),
    ("Louis Blanc", 402, 497 ),
    ("Louis Blanc", 402, 497 ),
    ("Louise Michel", 154, 512 ),
    ("Lourmel", 173, 279 ),
    ("Louvre Rivoli", 305, 392 ),
    ("Mabillon", 288, 347 ),
    ("Madeleine", 258, 422 ),
    ("Madeleine", 258, 422 ),
    ("Madeleine", 257, 423 ),
    ("Mairie d'Issy", 187, 226 ),
    ("Mairie d'Ivry", 439, 223 ),
    ("Mairie de Clichy", 215, 585 ),
    ("Mairie de Montreuil", 537, 412 ),
    ("Mairie de Saint-Ouen", 294, 606 ),
    ("Mairie des Lilas", 518, 474 ),
    ("Maison Blanche", 386, 232 ),
    ("Maisons-Alfort les Juilliottes", 509, 232 ),
    ("Maisons-Alfort Stade", 503, 240 ),
    ("Malakoff-Plateau de Vanves", 239, 225 ),
    ("Malakoff-Rue Étienne Dolet", 236, 212 ),
    ("Malesherbes", 213, 493 ),
    ("Maraîchers", 490, 375 ),
    ("Marcadet Poissonniers", 350, 554 ),
    ("Marcadet Poissonniers", 350, 554 ),
    ("Marcel Sembat", 90, 280  ),
    ("Marx Dormoy", 376, 557 ),
    ("Maubert Mutualité", 335, 334 ),
    ("Michel Ange Auteuil", 123, 334 ),
    ("Michel Ange Auteuil", 123, 334 ),
    ("Michel Ange Molitor", 119, 324 ),
    ("Michel Ange Molitor", 119, 324 ),
    ("Michel Bizot", 468, 308 ),
    ("Mirabeau", 141, 321 ),
    ("Miromesnil", 237, 440 ),
    ("Miromesnil", 237, 440 ),
    ("Monceau", 216, 474 ),
    ("Montgallet", 452, 328 ),
    ("Montparnasse Bienvenue", 266, 307 ),
    ("Montparnasse Bienvenue", 263, 300 ),
    ("Montparnasse Bienvenue", 267, 306 ),
    ("Montparnasse Bienvenue", 263, 300 ),
    ("Mouton-Duvernet", 295, 259 ),
    ("Ménilmontant", 448, 431 ),
    ("Nation", 464, 351 ),
    ("Nation", 466, 357 ),
    ("Nation", 463, 349 ),
    ("Nation", 466, 357 ),
    ("Nationale", 389, 258 ),
    ("Notre Dame de Lorette", 289, 502 ),
    ("Notre-Dame-des-Champs", 278, 320 ),
    ("Oberkampf", 417, 409 ),
    ("Oberkampf", 419, 411 ),
    ("Odéon", 300, 347 ),
    ("Odéon", 300, 348 ),
    ("Opéra", 284, 444 ),
    ("Opéra", 284, 444 ),
    ("Opéra", 284, 444 ),
    ("Ourcq", 442, 529 ),
    ("Palais Royal Musée du Louvre", 292, 395 ),
    ("Palais Royal Musée du Louvre", 293, 396 ),
    ("Parmentier", 421, 427 ),
    ("Passy", 157, 373 ),
    ("Pasteur", 243, 307 ),
    ("Pasteur", 243, 307 ),
    ("Pelleport", 489, 448 ),
    ("Pernety", 254, 269 ),
    ("Philippe-Auguste", 471, 403 ),
    ("Picpus", 473, 336 ),
    ("Pierre Curie", 430, 234 ),
    ("Pigalle", 303, 521 ),
    ("Pigalle", 303, 521 ),
    ("Place Balard", 162, 271 ),
    ("Place Monge", 360, 303 ),
    ("Place d'Italie", 368, 255 ),
    ("Place d'Italie", 368, 254 ),
    ("Place d'Italie", 368, 254 ),
    ("Place de Clichy", 259, 508 ),
    ("Place de Clichy", 259, 508 ),
    ("Place des Fêtes", 467, 481 ),
    ("Place des Fêtes", 467, 482 ),
    ("Plaisance", 249, 255 ),
    ("Poissonnière", 347, 484 ),
    ("Pont de Levallois Bécon", 125, 521 ),
    ("Pont de Neuilly", 104, 457 ),
    ("Pont de Sèvres", 66, 262  ),
    ("Pont-Marie", 349, 369 ),
    ("Pont-Neuf", 308, 382 ),
    ("Porte Dauphine", 134, 422 ),
    ("Porte Dorée", 478, 291 ),
    ("Porte Maillot", 132, 448 ),
    ("Porte d'Auteuil", 105, 336 ),
    ("Porte d'Italie", 394, 223 ),
    ("Porte d'Ivry", 419, 243 ),
    ("Porte d'Orléans", 285, 232 ),
    ("Porte de Bagnolet", 499, 430 ),
    ("Porte de Champerret", 172, 506 ),
    ("Porte de Charenton", 470, 285 ),
    ("Porte de Choisy", 405, 232 ),
    ("Porte de Clichy", 238, 558 ),
    ("Porte de Clignancourt", 350, 583 ),
    ("Porte de Montreuil", 498, 382 ),
    ("Porte de Pantin", 454, 538 ),
    ("Porte de Saint-Cloud", 101, 290 ),
    ("Porte de Saint-Ouen", 275, 583 ),
    ("Porte de Vanves", 245, 242 ),
    ("Porte de Versailles", 197, 258 ),
    ("Porte de Vincennes", 496, 341 ),
    ("Porte de la Chapelle", 384, 582 ),
    ("Porte de la Villette", 429, 579 ),
    ("Porte des Lilas", 498, 474 ),
    ("Porte des Lilas", 498, 474 ),
    ("Pré-Saint-Gervais", 484, 496 ),
    ("Pyramides", 284, 413 ),
    ("Pyramides", 284, 413 ),
    ("Pyrénées", 439, 466 ),
    ("Père Lachaise", 460, 416 ),
    ("Père Lachaise", 460, 416 ),
    ("Péreire", 187, 501 ),
    ("Quai de la Gare", 420, 282 ),
    ("Quai de la Rapée", 393, 328 ),
    ("Quatre Septembre", 300, 439 ),
    ("Rambuteau", 365, 402 ),
    ("Ranelagh", 133, 366 ),
    ("Raspail", 292, 289 ),
    ("Raspail", 291, 288 ),
    ("Rennes", 271, 333 ),
    ("Reuilly Diderot", 442, 339 ),
    ("Reuilly Diderot", 442, 339 ),
    ("Richard Lenoir", 412, 390 ),
    ("Richelieu Drouot", 307, 462 ),
    ("Richelieu Drouot", 307, 464 ),
    ("Riquet", 414, 534 ),
    ("Robespierre", 509, 390 ),
    ("Rome", 248, 499 ),
    ("Rue Montmartre Grands Boulevards", 324, 457 ),
    ("Rue Montmartre Grands Boulevards", 325, 459 ),
    ("Rue Saint-Maur", 437, 422 ),
    ("Rue de la Pompe", 144, 397 ),
    ("Rue des Boulets", 449, 373 ),
    ("Rue du Bac", 258, 358 ),
    ("Réaumur Sébastopol", 351, 422 ),
    ("Réaumur Sébastopol", 351, 422 ),
    ("République", 398, 433 ),
    ("République", 397, 433 ),
    ("République", 398, 434 ),
    ("République", 395, 432 ),
    ("République", 399, 435 ),
    ("Saint-Ambroise", 428, 401 ),
    ("Saint-Augustin", 254, 452 ),
    ("Saint-Denis-Porte de Paris", 332, 637 ),
    ("Saint-Denis-Université", 365, 664 ),
    ("Saint-Fargeau", 494, 460 ),
    ("Saint-Francois Xavier", 237, 359 ),
    ("Saint-Georges", 300, 511 ),
    ("Saint-Germain-des-Prés", 284, 352 ),
    ("Saint-Jacques", 321, 269 ),
    ("Saint-Lazare", 259, 478 ),
    ("Saint-Lazare", 258, 478 ),
    ("Saint-Lazare", 258, 477 ),
    ("Saint-Mandé Tourelle", 511, 336 ),
    ("Saint-Marcel", 381, 295 ),
    ("Saint-Michel", 312, 349 ),
    ("Saint-Paul Le Marais", 380, 367 ),
    ("Saint-Philippe du Roule", 225, 430 ),
    ("Saint-Placide", 272, 324 ),
    ("Saint-Sulpice", 275, 336 ),
    ("Saint-Sébastien-Froissart", 397, 394 ),
    ("Sentier", 327, 430 ),
    ("Simplon", 350, 568 ),
    ("Solférino", 258, 371 ),
    ("Stalingrad", 408, 516 ),
    ("Stalingrad", 409, 518 ),
    ("Stalingrad", 408, 517 ),
    ("Strasbourg Saint-Denis", 359, 447 ),
    ("Strasbourg Saint-Denis", 359, 445 ),
    ("Strasbourg Saint-Denis", 359, 447 ),
    ("Sully Morland", 374, 354 ),
    ("Sèvres Babylone", 260, 347 ),
    ("Sèvres Babylone", 260, 347 ),
    ("Sèvres Lecourbe", 232, 321 ),
    ("Ségur", 217, 336 ),
    ("Temple", 384, 427 ),
    ("Ternes", 188, 451 ),
    ("Tolbiac", 376, 242 ),
    ("Trinité d'Estienne d'Orves", 280, 495 ),
    ("Trocadéro", 157, 397 ),
    ("Trocadéro", 157, 397 ),
    ("Tuileries", 278, 400 ),
    ("Télégraphe", 476, 480 ),
    ("Vaneau", 248, 343 ),
    ("Varenne", 237, 368 ),
    ("Vaugirard", 217, 285 ),
    ("Vavin", 282, 300 ),
    ("Victor Hugo", 148, 418 ),
    ("Villejuif Louis Aragon", 385, 184 ),
    ("Villejuif Léo Lagrange", 385, 206 ),
    ("Villejuif P. Vaillant Couturier", 385, 195 ),
    ("Villiers", 232, 486 ),
    ("Villiers", 231, 487 ),
    ("Volontaires", 231, 297 ),
    ("Voltaire", 435, 393 ),
    ("Wagram", 200, 497 ),
    ("École Militaire", 209, 350 ),
    ("École Vétérinaire de Maisons-Alfort", 495, 250 ),
    ("Église d'Auteuil", 137, 330 ),
    ("Église de Pantin", 484, 563 ),
    ("Étienne Marcel", 347, 412 ))

  val legData = List(
    // (source, destination, time-in-seconds)
    // source and destination are indices into stationPositions Vector
    (0, 238, 41 ),
    (0, 159, 46 ),
    (1, 12, 36 ),
    (1, 235, 44 ),
    (2, 110, 69 ),
    (2, 139, 50 ),
    (3, 262, 33 ),
    (3, 210, 41 ),
    (4, 171, 43 ),
    (4, 251, 35 ),
    (5, 13, 67 ),
    (5, 239, 54 ),
    (6, 55, 56 ),
    (6, 258, 46 ),
    (7, 290, 39 ),
    (7, 311, 79 ),
    (8, 350, 38 ),
    (8, 309, 58 ),
    (9, 338, 28 ),
    (9, 78, 62 ),
    (10, 277, 42 ),
    (10, 108, 36 ),
    (11, 154, 32 ),
    (11, 54, 41 ),
    (12, 213, 42 ),
    (12, 1, 36 ),
    (13, 151, 57 ),
    (13, 5, 67 ),
    (14, 124, 46 ),
    (14, 64, 28 ),
    (15, 318, 37 ),
    (15, 319, 73 ),
    (16, 119, 98 ),
    (16, 331, 62 ),
    (17, 288, 89 ),
    (17, 40, 41 ),
    (18, 163, 32 ),
    (18, 61, 56 ),
    (19, 236, 31 ),
    (19, 93, 33 ),
    (20, 129, 48 ),
    (20, 283, 38 ),
    (21, 86, 46 ),
    (21, 75, 51 ),
    (22, 84, 39 ),
    (22, 120, 81 ),
    (23, 97, 35 ),
    (23, 287, 57 ),
    (24, 84, 51 ),
    (25, 193, 42 ),
    (25, 253, 35 ),
    (26, 98, 31 ),
    (26, 230, 64 ),
    (27, 239, 64 ),
    (27, 246, 59 ),
    (28, 29, 79 ),
    (29, 374, 46 ),
    (374, 29, 46 ),
    (29, 28, 79 ),
    (30, 354, 24 ),
    (30, 150, 44 ),
    (31, 41, 28 ),
    (31, 144, 36 ),
    (32, 343, 54 ),
    (32, 303, 41 ),
    (33, 344, 52 ),
    (33, 304, 41 ),
    (34, 248, 36 ),
    (34, 41, 57 ),
    (35, 111, 34 ),
    (35, 172, 40 ),
    (36, 198, 105 ),
    (36, 37, 39 ),
    (37, 36, 39 ),
    (38, 336, 35 ),
    (38, 289, 38 ),
    (39, 153, 38 ),
    (39, 267, 46 ),
    (40, 17, 41 ),
    (40, 297, 37 ),
    (41, 34, 57 ),
    (41, 31, 28 ),
    (42, 190, 42 ),
    (42, 215, 35 ),
    (43, 66, 41 ),
    (43, 328, 57 ),
    (44, 162, 54 ),
    (44, 250, 64 ),
    (45, 348, 43 ),
    (45, 155, 35 ),
    (46, 242, 63 ),
    (46, 329, 46 ),
    (47, 148, 46 ),
    (47, 195, 41 ),
    (48, 182, 66 ),
    (48, 318, 60 ),
    (49, 164, 42 ),
    (49, 241, 40 ),
    (50, 77, 54 ),
    (50, 109, 62 ),
    (51, 137, 62 ),
    (51, 202, 69 ),
    (52, 201, 20 ),
    (53, 372, 47 ),
    (53, 167, 33 ),
    (54, 11, 41 ),
    (54, 145, 51 ),
    (55, 127, 60 ),
    (55, 6, 56 ),
    (56, 351, 59 ),
    (56, 362, 75 ),
    (57, 150, 35 ),
    (58, 307, 31 ),
    (58, 369, 31 ),
    (59, 224, 72 ),
    (59, 162, 53 ),
    (60, 299, 63 ),
    (60, 133, 45 ),
    (61, 18, 56 ),
    (61, 335, 28 ),
    (62, 287, 53 ),
    (62, 216, 48 ),
    (63, 123, 44 ),
    (63, 169, 57 ),
    (64, 14, 28 ),
    (64, 192, 57 ),
    (65, 342, 41 ),
    (65, 121, 57 ),
    (66, 43, 41 ),
    (67, 135, 87 ),
    (67, 173, 51 ),
    (68, 136, 85 ),
    (69, 120, 287 ),
    (69, 281, 131 ),
    (70, 73, 45 ),
    (70, 165, 66 ),
    (71, 254, 73 ),
    (71, 255, 37 ),
    (72, 188, 52 ),
    (73, 330, 46 ),
    (73, 70, 45 ),
    (74, 195, 44 ),
    (74, 221, 52 ),
    (75, 21, 51 ),
    (75, 142, 93 ),
    (76, 156, 59 ),
    (76, 111, 30 ),
    (77, 356, 57 ),
    (77, 50, 54 ),
    (78, 9, 62 ),
    (78, 175, 41 ),
    (79, 177, 41 ),
    (79, 138, 71 ),
    (80, 274, 41 ),
    (80, 360, 49 ),
    (81, 178, 43 ),
    (81, 274, 43 ),
    (82, 87, 32 ),
    (82, 277, 47 ),
    (83, 243, 36 ),
    (83, 128, 49 ),
    (84, 24, 51 ),
    (84, 22, 39 ),
    (85, 204, 47 ),
    (85, 351, 46 ),
    (86, 211, 37 ),
    (86, 21, 46 ),
    (87, 300, 43 ),
    (87, 82, 32 ),
    (88, 181, 44 ),
    (88, 301, 47 ),
    (89, 90, 29 ),
    (90, 89, 29 ),
    (90, 91, 26 ),
    (91, 90, 26 ),
    (91, 185, 27 ),
    (92, 34, 37 ),
    (93, 19, 33 ),
    (93, 97, 45 ),
    (94, 200, 27 ),
    (94, 205, 39 ),
    (95, 210, 48 ),
    (95, 292, 37 ),
    (96, 324, 52 ),
    (96, 293, 42 ),
    (97, 93, 45 ),
    (97, 23, 35 ),
    (98, 155, 69 ),
    (98, 26, 31 ),
    (99, 358, 31 ),
    (99, 349, 54 ),
    (100, 207, 113 ),
    (100, 321, 59 ),
    (101, 293, 43 ),
    (101, 209, 36 ),
    (102, 252, 35 ),
    (102, 130, 37 ),
    (103, 327, 44 ),
    (103, 367, 30 ),
    (104, 199, 62 ),
    (104, 271, 40 ),
    (105, 296, 42 ),
    (105, 163, 47 ),
    (106, 231, 29 ),
    (106, 206, 36 ),
    (107, 335, 49 ),
    (107, 314, 49 ),
    (108, 10, 36 ),
    (108, 152, 29 ),
    (109, 50, 62 ),
    (109, 127, 59 ),
    (110, 332, 37 ),
    (110, 2, 69 ),
    (111, 76, 30 ),
    (111, 35, 34 ),
    (112, 180, 71 ),
    (113, 234, 36 ),
    (113, 207, 47 ),
    (114, 263, 72 ),
    (115, 263, 39 ),
    (115, 285, 70 ),
    (116, 233, 50 ),
    (117, 148, 59 ),
    (118, 329, 60 ),
    (118, 288, 30 ),
    (119, 295, 41 ),
    (119, 16, 98 ),
    (120, 22, 81 ),
    (120, 69, 287 ),
    (121, 65, 57 ),
    (121, 124, 67 ),
    (122, 140, 103 ),
    (122, 125, 59 ),
    (123, 250, 51 ),
    (123, 63, 44 ),
    (124, 121, 67 ),
    (124, 14, 46 ),
    (125, 122, 59 ),
    (125, 340, 107 ),
    (126, 272, 37 ),
    (126, 182, 42 ),
    (127, 109, 59 ),
    (127, 55, 60 ),
    (128, 83, 49 ),
    (128, 324, 42 ),
    (129, 311, 49 ),
    (129, 20, 48 ),
    (130, 102, 37 ),
    (131, 153, 72 ),
    (131, 272, 63 ),
    (132, 223, 68 ),
    (132, 327, 40 ),
    (133, 60, 45 ),
    (133, 317, 49 ),
    (134, 270, 47 ),
    (134, 374, 53 ),
    (135, 331, 65 ),
    (135, 67, 87 ),
    (136, 68, 85 ),
    (136, 290, 73 ),
    (137, 359, 54 ),
    (137, 51, 62 ),
    (138, 79, 71 ),
    (138, 158, 57 ),
    (139, 2, 50 ),
    (139, 355, 46 ),
    (140, 313, 47 ),
    (140, 122, 103 ),
    (141, 291, 37 ),
    (141, 197, 49 ),
    (142, 75, 93 ),
    (142, 339, 27 ),
    (143, 340, 29 ),
    (143, 160, 44 ),
    (144, 31, 36 ),
    (144, 170, 46 ),
    (145, 54, 51 ),
    (145, 373, 55 ),
    (146, 283, 33 ),
    (146, 247, 50 ),
    (147, 159, 44 ),
    (147, 191, 49 ),
    (148, 117, 59 ),
    (148, 47, 46 ),
    (149, 241, 57 ),
    (149, 345, 79 ),
    (150, 30, 44 ),
    (150, 57, 35 ),
    (151, 339, 93 ),
    (151, 13, 57 ),
    (152, 108, 29 ),
    (153, 245, 64 ),
    (153, 131, 72 ),
    (153, 39, 38 ),
    (154, 349, 33 ),
    (154, 11, 32 ),
    (155, 45, 35 ),
    (155, 98, 69 ),
    (156, 371, 46 ),
    (156, 76, 59 ),
    (157, 306, 36 ),
    (157, 291, 49 ),
    (158, 138, 57 ),
    (158, 371, 66 ),
    (159, 0, 46 ),
    (159, 147, 44 ),
    (160, 143, 44 ),
    (160, 226, 40 ),
    (161, 364, 28 ),
    (161, 184, 39 ),
    (162, 59, 53 ),
    (162, 44, 54 ),
    (163, 105, 47 ),
    (163, 18, 32 ),
    (164, 244, 60 ),
    (164, 49, 42 ),
    (165, 70, 66 ),
    (165, 375, 33 ),
    (166, 258, 38 ),
    (166, 252, 37 ),
    (167, 53, 33 ),
    (167, 265, 31 ),
    (168, 326, 49 ),
    (168, 245, 28 ),
    (169, 63, 57 ),
    (169, 341, 54 ),
    (170, 144, 46 ),
    (171, 264, 49 ),
    (171, 4, 43 ),
    (172, 35, 40 ),
    (172, 240, 35 ),
    (173, 67, 51 ),
    (173, 227, 34 ),
    (174, 221, 31 ),
    (174, 346, 72 ),
    (175, 78, 41 ),
    (175, 325, 144 ),
    (176, 281, 71 ),
    (177, 225, 88 ),
    (177, 79, 41 ),
    (178, 81, 43 ),
    (179, 237, 37 ),
    (180, 267, 91 ),
    (180, 112, 71 ),
    (181, 88, 44 ),
    (182, 126, 42 ),
    (182, 48, 66 ),
    (183, 278, 51 ),
    (184, 260, 31 ),
    (184, 161, 39 ),
    (184, 352, 36 ),
    (185, 91, 27 ),
    (185, 186, 26 ),
    (186, 185, 26 ),
    (186, 372, 33 ),
    (187, 188, 34 ),
    (187, 273, 46 ),
    (188, 72, 52 ),
    (188, 187, 34 ),
    (189, 367, 49 ),
    (189, 370, 35 ),
    (190, 269, 27 ),
    (190, 42, 42 ),
    (191, 147, 49 ),
    (191, 194, 67 ),
    (192, 64, 57 ),
    (192, 337, 36 ),
    (193, 271, 38 ),
    (193, 25, 42 ),
    (194, 191, 67 ),
    (194, 276, 67 ),
    (195, 47, 41 ),
    (195, 74, 44 ),
    (196, 259, 47 ),
    (197, 141, 49 ),
    (197, 199, 28 ),
    (198, 52, 42 ),
    (199, 197, 28 ),
    (199, 104, 62 ),
    (200, 257, 51 ),
    (200, 94, 27 ),
    (201, 145, 46 ),
    (202, 51, 69 ),
    (202, 326, 112 ),
    (203, 317, 53 ),
    (203, 332, 40 ),
    (204, 366, 51 ),
    (204, 85, 47 ),
    (205, 94, 39 ),
    (205, 296, 38 ),
    (206, 106, 36 ),
    (206, 218, 45 ),
    (207, 113, 47 ),
    (207, 100, 113 ),
    (208, 361, 42 ),
    (208, 333, 48 ),
    (209, 101, 36 ),
    (209, 232, 54 ),
    (210, 3, 41 ),
    (210, 95, 48 ),
    (211, 284, 49 ),
    (211, 86, 37 ),
    (212, 275, 86 ),
    (212, 295, 64 ),
    (213, 12, 42 ),
    (214, 236, 42 ),
    (215, 42, 35 ),
    (215, 307, 60 ),
    (216, 62, 48 ),
    (216, 243, 55 ),
    (217, 353, 29 ),
    (217, 322, 37 ),
    (218, 206, 45 ),
    (218, 294, 38 ),
    (219, 297, 50 ),
    (219, 313, 81 ),
    (220, 316, 35 ),
    (220, 315, 80 ),
    (221, 74, 52 ),
    (221, 174, 31 ),
    (222, 323, 42 ),
    (222, 330, 31 ),
    (223, 289, 43 ),
    (223, 132, 68 ),
    (224, 282, 80 ),
    (224, 59, 72 ),
    (225, 298, 75 ),
    (225, 177, 88 ),
    (226, 160, 40 ),
    (226, 270, 39 ),
    (227, 173, 34 ),
    (227, 356, 38 ),
    (228, 255, 53 ),
    (228, 282, 49 ),
    (229, 305, 43 ),
    (229, 312, 64 ),
    (230, 26, 64 ),
    (230, 354, 62 ),
    (231, 368, 40 ),
    (231, 106, 29 ),
    (232, 209, 54 ),
    (232, 348, 46 ),
    (233, 320, 33 ),
    (233, 116, 50 ),
    (234, 249, 38 ),
    (234, 113, 36 ),
    (235, 1, 44 ),
    (235, 284, 44 ),
    (236, 214, 42 ),
    (236, 19, 31 ),
    (237, 179, 37 ),
    (237, 261, 37 ),
    (238, 322, 27 ),
    (238, 0, 41 ),
    (239, 5, 54 ),
    (239, 27, 64 ),
    (240, 172, 35 ),
    (241, 49, 40 ),
    (241, 149, 57 ),
    (242, 46, 63 ),
    (243, 216, 55 ),
    (243, 83, 36 ),
    (244, 352, 37 ),
    (244, 164, 60 ),
    (245, 168, 28 ),
    (245, 153, 64 ),
    (246, 27, 59 ),
    (246, 302, 37 ),
    (247, 146, 50 ),
    (247, 357, 23 ),
    (248, 280, 57 ),
    (249, 273, 35 ),
    (249, 234, 38 ),
    (250, 44, 64 ),
    (250, 123, 51 ),
    (251, 4, 35 ),
    (252, 166, 37 ),
    (252, 102, 35 ),
    (253, 25, 35 ),
    (254, 345, 75 ),
    (254, 71, 73 ),
    (255, 71, 37 ),
    (255, 228, 53 ),
    (256, 362, 37 ),
    (257, 265, 26 ),
    (257, 200, 51 ),
    (258, 6, 46 ),
    (258, 166, 38 ),
    (259, 198, 47 ),
    (259, 36, 84 ),
    (260, 266, 37 ),
    (260, 184, 31 ),
    (261, 237, 37 ),
    (261, 266, 46 ),
    (262, 3, 33 ),
    (263, 114, 72 ),
    (263, 115, 39 ),
    (264, 286, 41 ),
    (264, 171, 49 ),
    (265, 167, 31 ),
    (265, 257, 26 ),
    (266, 261, 46 ),
    (266, 260, 37 ),
    (267, 39, 46 ),
    (267, 180, 91 ),
    (268, 337, 39 ),
    (269, 301, 35 ),
    (269, 190, 27 ),
    (270, 226, 39 ),
    (270, 134, 47 ),
    (271, 104, 40 ),
    (271, 193, 38 ),
    (272, 131, 63 ),
    (272, 126, 37 ),
    (273, 187, 46 ),
    (273, 249, 35 ),
    (274, 81, 43 ),
    (274, 80, 41 ),
    (275, 328, 41 ),
    (275, 212, 86 ),
    (276, 194, 67 ),
    (277, 82, 47 ),
    (277, 10, 42 ),
    (278, 357, 59 ),
    (278, 183, 51 ),
    (279, 320, 37 ),
    (280, 92, 43 ),
    (281, 69, 131 ),
    (281, 176, 71 ),
    (282, 228, 49 ),
    (282, 224, 80 ),
    (283, 20, 38 ),
    (283, 146, 33 ),
    (284, 235, 44 ),
    (284, 211, 49 ),
    (285, 115, 70 ),
    (285, 305, 61 ),
    (286, 370, 35 ),
    (286, 264, 41 ),
    (287, 23, 57 ),
    (287, 62, 53 ),
    (288, 118, 30 ),
    (288, 17, 89 ),
    (289, 38, 38 ),
    (289, 223, 43 ),
    (290, 136, 73 ),
    (290, 7, 39 ),
    (291, 157, 49 ),
    (291, 141, 37 ),
    (292, 95, 37 ),
    (292, 361, 38 ),
    (293, 96, 42 ),
    (293, 101, 43 ),
    (294, 218, 38 ),
    (294, 347, 46 ),
    (295, 212, 64 ),
    (295, 119, 41 ),
    (296, 205, 38 ),
    (296, 105, 42 ),
    (297, 40, 37 ),
    (297, 219, 50 ),
    (298, 303, 46 ),
    (298, 225, 75 ),
    (299, 304, 48 ),
    (299, 60, 63 ),
    (300, 341, 46 ),
    (300, 87, 43 ),
    (301, 88, 47 ),
    (301, 269, 35 ),
    (302, 246, 37 ),
    (302, 366, 53 ),
    (303, 32, 41 ),
    (303, 298, 46 ),
    (304, 33, 41 ),
    (304, 299, 48 ),
    (305, 285, 61 ),
    (305, 229, 43 ),
    (306, 355, 33 ),
    (306, 157, 36 ),
    (307, 215, 60 ),
    (307, 58, 31 ),
    (308, 347, 29 ),
    (308, 338, 33 ),
    (309, 8, 58 ),
    (309, 336, 65 ),
    (310, 375, 28 ),
    (310, 342, 67 ),
    (311, 7, 79 ),
    (311, 129, 49 ),
    (312, 229, 64 ),
    (312, 350, 37 ),
    (313, 219, 81 ),
    (313, 140, 47 ),
    (314, 107, 49 ),
    (314, 343, 98 ),
    (315, 220, 80 ),
    (315, 344, 107 ),
    (316, 369, 27 ),
    (316, 220, 35 ),
    (317, 133, 49 ),
    (317, 203, 53 ),
    (318, 48, 60 ),
    (318, 15, 37 ),
    (319, 15, 73 ),
    (320, 279, 37 ),
    (320, 233, 33 ),
    (321, 100, 59 ),
    (321, 359, 23 ),
    (322, 217, 37 ),
    (322, 238, 27 ),
    (323, 334, 47 ),
    (323, 222, 42 ),
    (324, 128, 42 ),
    (324, 96, 52 ),
    (325, 175, 144 ),
    (325, 353, 69 ),
    (326, 202, 112 ),
    (326, 168, 49 ),
    (327, 132, 40 ),
    (327, 103, 44 ),
    (328, 43, 57 ),
    (328, 275, 41 ),
    (329, 46, 46 ),
    (329, 118, 60 ),
    (330, 222, 31 ),
    (330, 73, 46 ),
    (331, 16, 62 ),
    (331, 135, 65 ),
    (332, 203, 40 ),
    (332, 110, 37 ),
    (333, 208, 48 ),
    (333, 334, 32 ),
    (334, 333, 32 ),
    (334, 323, 47 ),
    (335, 61, 28 ),
    (335, 107, 49 ),
    (336, 309, 65 ),
    (336, 38, 35 ),
    (337, 192, 36 ),
    (337, 268, 39 ),
    (338, 308, 33 ),
    (338, 9, 28 ),
    (339, 142, 27 ),
    (339, 151, 93 ),
    (340, 125, 107 ),
    (340, 143, 29 ),
    (341, 169, 54 ),
    (341, 300, 46 ),
    (342, 310, 67 ),
    (342, 65, 41 ),
    (343, 314, 98 ),
    (343, 32, 54 ),
    (344, 315, 107 ),
    (344, 33, 52 ),
    (345, 149, 79 ),
    (345, 254, 75 ),
    (346, 174, 72 ),
    (346, 358, 33 ),
    (347, 294, 46 ),
    (347, 308, 29 ),
    (348, 232, 46 ),
    (348, 45, 43 ),
    (349, 99, 54 ),
    (349, 154, 33 ),
    (350, 312, 37 ),
    (350, 8, 38 ),
    (351, 85, 46 ),
    (351, 56, 59 ),
    (352, 184, 36 ),
    (352, 244, 37 ),
    (353, 325, 69 ),
    (353, 217, 29 ),
    (354, 230, 62 ),
    (354, 30, 24 ),
    (355, 139, 46 ),
    (355, 306, 33 ),
    (356, 227, 38 ),
    (356, 77, 57 ),
    (357, 247, 23 ),
    (357, 278, 59 ),
    (358, 346, 33 ),
    (358, 99, 31 ),
    (359, 321, 23 ),
    (359, 137, 54 ),
    (360, 80, 49 ),
    (360, 368, 47 ),
    (361, 292, 38 ),
    (361, 208, 42 ),
    (362, 56, 75 ),
    (362, 256, 37 ),
    (363, 365, 28 ),
    (364, 365, 28 ),
    (364, 161, 28 ),
    (365, 363, 28 ),
    (365, 364, 28 ),
    (366, 302, 53 ),
    (366, 204, 51 ),
    (367, 103, 30 ),
    (367, 189, 49 ),
    (368, 360, 47 ),
    (368, 231, 40 ),
    (369, 58, 31 ),
    (369, 316, 27 ),
    (370, 189, 35 ),
    (370, 286, 35 ),
    (371, 158, 66 ),
    (371, 156, 46 ),
    (372, 186, 33 ),
    (372, 53, 47 ),
    (373, 196, 37 ),
    (374, 134, 53 ),
    (375, 165, 33 ),
    (375, 310, 28 ),
    // changing metro lines at a station
    (7, 8, 120 ),
    (8, 7, 120 ),
    (13, 14, 120 ),
    (14, 13, 120 ),
    (16, 18, 120 ),
    (16, 17, 120 ),
    (17, 18, 120 ),
    (17, 16, 120 ),
    (18, 16, 120 ),
    (18, 17, 120 ),
    (20, 21, 120 ),
    (21, 20, 120 ),
    (22, 23, 120 ),
    (23, 22, 120 ),
    (32, 33, 120 ),
    (33, 32, 120 ),
    (50, 51, 120 ),
    (51, 50, 120 ),
    (55, 57, 120 ),
    (55, 56, 120 ),
    (56, 57, 120 ),
    (56, 55, 120 ),
    (57, 55, 120 ),
    (57, 56, 120 ),
    (59, 60, 120 ),
    (60, 59, 120 ),
    (67, 69, 120 ),
    (67, 68, 120 ),
    (67, 70, 120 ),
    (67, 71, 120 ),
    (68, 69, 120 ),
    (68, 70, 120 ),
    (68, 71, 120 ),
    (68, 67, 120 ),
    (69, 67, 120 ),
    (69, 70, 120 ),
    (69, 71, 120 ),
    (69, 68, 120 ),
    (70, 71, 120 ),
    (70, 69, 120 ),
    (70, 68, 120 ),
    (70, 67, 120 ),
    (71, 70, 120 ),
    (71, 69, 120 ),
    (71, 68, 120 ),
    (71, 67, 120 ),
    (77, 79, 120 ),
    (77, 78, 120 ),
    (78, 79, 120 ),
    (78, 77, 120 ),
    (79, 77, 120 ),
    (79, 78, 120 ),
    (93, 94, 120 ),
    (94, 93, 120 ),
    (95, 96, 120 ),
    (96, 95, 120 ),
    (99, 100, 120 ),
    (100, 99, 120 ),
    (109, 110, 120 ),
    (110, 109, 120 ),
    (115, 116, 120 ),
    (116, 115, 120 ),
    (117, 118, 120 ),
    (118, 117, 120 ),
    (119, 120, 120 ),
    (120, 119, 120 ),
    (121, 123, 120 ),
    (121, 122, 120 ),
    (122, 123, 120 ),
    (122, 121, 120 ),
    (123, 121, 120 ),
    (123, 122, 120 ),
    (124, 125, 120 ),
    (125, 124, 120 ),
    (132, 133, 120 ),
    (133, 132, 120 ),
    (135, 136, 120 ),
    (136, 135, 120 ),
    (137, 138, 120 ),
    (138, 137, 120 ),
    (142, 144, 120 ),
    (142, 143, 120 ),
    (143, 144, 120 ),
    (143, 142, 120 ),
    (144, 142, 120 ),
    (144, 143, 120 ),
    (148, 149, 120 ),
    (149, 148, 120 ),
    (154, 156, 120 ),
    (154, 155, 120 ),
    (155, 156, 120 ),
    (155, 154, 120 ),
    (156, 154, 120 ),
    (156, 155, 120 ),
    (169, 170, 120 ),
    (170, 169, 120 ),
    (175, 177, 120 ),
    (175, 176, 120 ),
    (176, 177, 120 ),
    (176, 175, 120 ),
    (177, 175, 120 ),
    (177, 176, 120 ),
    (191, 192, 120 ),
    (192, 191, 120 ),
    (196, 197, 120 ),
    (197, 196, 120 ),
    (198, 199, 120 ),
    (199, 198, 120 ),
    (202, 203, 120 ),
    (203, 202, 120 ),
    (206, 208, 120 ),
    (206, 207, 120 ),
    (206, 209, 120 ),
    (207, 208, 120 ),
    (207, 209, 120 ),
    (207, 206, 120 ),
    (208, 206, 120 ),
    (208, 209, 120 ),
    (208, 207, 120 ),
    (209, 208, 120 ),
    (209, 207, 120 ),
    (209, 206, 120 ),
    (212, 214, 120 ),
    (212, 213, 120 ),
    (212, 215, 120 ),
    (213, 214, 120 ),
    (213, 215, 120 ),
    (213, 212, 120 ),
    (214, 212, 120 ),
    (214, 215, 120 ),
    (214, 213, 120 ),
    (215, 214, 120 ),
    (215, 213, 120 ),
    (215, 212, 120 ),
    (219, 220, 120 ),
    (220, 219, 120 ),
    (221, 222, 120 ),
    (222, 221, 120 ),
    (223, 225, 120 ),
    (223, 224, 120 ),
    (224, 225, 120 ),
    (224, 223, 120 ),
    (225, 223, 120 ),
    (225, 224, 120 ),
    (227, 228, 120 ),
    (228, 227, 120 ),
    (231, 232, 120 ),
    (232, 231, 120 ),
    (238, 239, 120 ),
    (239, 238, 120 ),
    (242, 244, 120 ),
    (242, 243, 120 ),
    (243, 244, 120 ),
    (243, 242, 120 ),
    (244, 242, 120 ),
    (244, 243, 120 ),
    (245, 246, 120 ),
    (246, 245, 120 ),
    (247, 248, 120 ),
    (248, 247, 120 ),
    (278, 279, 120 ),
    (279, 278, 120 ),
    (281, 282, 120 ),
    (282, 281, 120 ),
    (284, 285, 120 ),
    (285, 284, 120 ),
    (292, 293, 120 ),
    (293, 292, 120 ),
    (295, 296, 120 ),
    (296, 295, 120 ),
    (298, 299, 120 ),
    (299, 298, 120 ),
    (303, 304, 120 ),
    (304, 303, 120 ),
    (309, 310, 120 ),
    (310, 309, 120 ),
    (311, 313, 120 ),
    (311, 312, 120 ),
    (311, 314, 120 ),
    (311, 315, 120 ),
    (312, 313, 120 ),
    (312, 314, 120 ),
    (312, 315, 120 ),
    (312, 311, 120 ),
    (313, 311, 120 ),
    (313, 314, 120 ),
    (313, 315, 120 ),
    (313, 312, 120 ),
    (314, 315, 120 ),
    (314, 313, 120 ),
    (314, 312, 120 ),
    (314, 311, 120 ),
    (315, 314, 120 ),
    (315, 313, 120 ),
    (315, 312, 120 ),
    (315, 311, 120 ),
    (325, 327, 120 ),
    (325, 326, 120 ),
    (326, 327, 120 ),
    (326, 325, 120 ),
    (327, 325, 120 ),
    (327, 326, 120 ),
    (339, 341, 120 ),
    (339, 340, 120 ),
    (340, 341, 120 ),
    (340, 339, 120 ),
    (341, 339, 120 ),
    (341, 340, 120 ),
    (342, 344, 120 ),
    (342, 343, 120 ),
    (343, 344, 120 ),
    (343, 342, 120 ),
    (344, 342, 120 ),
    (344, 343, 120 ),
    (346, 347, 120 ),
    (347, 346, 120 ),
    (354, 355, 120 ),
    (355, 354, 120 ),
    (366, 367, 120 ),
    (367, 366, 120 )
  )
}
